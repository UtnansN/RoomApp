package com.example.spaceapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.spaceapp.AuthInterceptor
import com.example.spaceapp.Constants
import com.example.spaceapp.data.dao.SpaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoginPreferences

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
        .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
//        return Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "spaces.db")
//            .allowMainThreadQueries()
//            .build()
//    }

    @Provides
    @LoginPreferences
    fun provideLoginPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREF_LOGIN, Context.MODE_PRIVATE)
    }

}