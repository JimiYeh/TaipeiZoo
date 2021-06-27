package com.cloudinteractive.taipeizoo.repository

import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.network.ApiResponse
import com.cloudinteractive.taipeizoo.network.Client
import com.cloudinteractive.taipeizoo.network.callApi

class ZooRepository {

    suspend fun fetchAreaList(): ApiResponse<GetAreaListResp> =
        callApi { Client.zooApiService.getAreaList() }


    suspend fun fetchPlantList(keyword: String, offset: Int) =
        callApi {
            Client.zooApiService.getPlantList(
                keyword = keyword,
                offset = offset
            )
        }
}