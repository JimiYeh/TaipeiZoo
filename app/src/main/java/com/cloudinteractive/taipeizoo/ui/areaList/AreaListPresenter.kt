package com.cloudinteractive.taipeizoo.ui.areaList

import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.network.ApiResponse
import com.cloudinteractive.taipeizoo.network.Client
import com.cloudinteractive.taipeizoo.network.callApi

class AreaListPresenter(val view: AreaListContract.View) : AreaListContract.Presenter {

    override suspend fun fetchAreaList() {
        view.showLoading(true)

        val resp = callApi { Client.zooApiService.getAreaList() }

        view.showLoading(false)
        when (resp) {
            is ApiResponse.ApiSuccess<GetAreaListResp> -> {
                view.showArea(resp.data?.result?.areas ?: listOf())
            }

            is ApiResponse.ApiError -> {
                view.showErrorMessage(resp.responseBody?.toString() ?: "API fail")
            }

            is ApiResponse.ApiException -> {
                view.showErrorMessage(resp.exception.message ?: "unknown exception")
            }
        }
    }
}