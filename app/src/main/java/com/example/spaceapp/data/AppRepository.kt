package com.example.spaceapp.data

import androidx.lifecycle.LiveData
import com.example.spaceapp.data.model.local.Space
import retrofit2.Call
import javax.inject.Inject

class AppRepository @Inject constructor(private val webService: WebService,
                                        private val localDatabase: LocalDatabase) {

    fun createSpace(space: Space): Call<Space> {
        return webService.createSpace(space)
    }

    fun getLocalSpaces(): LiveData<List<Space>> {
        return localDatabase.spaceDao().getAll()
    }

    fun getUpdatedSpaces(): Call<List<Space>> {
        return webService.getSpaces()
    }

    fun refreshSpaces(spaces: List<Space>) {
        localDatabase.spaceDao().insertAll(spaces)
    }

}