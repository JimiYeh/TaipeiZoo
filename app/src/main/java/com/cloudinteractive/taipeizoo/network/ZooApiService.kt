package com.cloudinteractive.taipeizoo.network

import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.model.plant.GetPlantListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ZooApiService {

    @GET("/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    suspend fun getAreaList(
        @Query("scope") scope: String = "resourceAquire",
    ): Response<GetAreaListResp>

    @GET("/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14")
    suspend fun getPlantList(
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") keyword: String,    //
        @Query("limit") limit: Int = 20,    // set default size to 20 per request query
        @Query("offset") offset: Int
    ): Response<GetPlantListResp>

}