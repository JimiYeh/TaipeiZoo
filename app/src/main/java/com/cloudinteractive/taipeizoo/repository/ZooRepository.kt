package com.cloudinteractive.taipeizoo.repository

import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.network.ApiResponse
import com.cloudinteractive.taipeizoo.network.Client

class ZooRepository {

    suspend fun fetchAreaList(): ApiResponse<GetAreaListResp> = Client.zooApiService.getAreaList()


    suspend fun fetchPlantList(keyword: String, offset: Int) =
        Client.zooApiService.getPlantList(
            keyword = keyword,
            offset = offset
        )

}