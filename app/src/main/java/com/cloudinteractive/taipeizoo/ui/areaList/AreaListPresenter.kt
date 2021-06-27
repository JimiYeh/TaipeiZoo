package com.cloudinteractive.taipeizoo.ui.areaList

import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.network.ApiResponse
import com.cloudinteractive.taipeizoo.repository.ZooRepository

class AreaListPresenter(private val view: AreaListContract.View) : AreaListContract.Presenter {

    override suspend fun fetchAreaList() {
        view.showLoading(true)

        val resp = ZooRepository().fetchAreaList()

        view.showLoading(false)
        when (resp) {
            is ApiResponse.ApiSuccess<GetAreaListResp> -> {
                view.showArea(resp.data.result.areas)
            }

            is ApiResponse.ApiError -> {
                view.showErrorMessage("${resp.httpCode} error")
            }

            is ApiResponse.ApiException -> {
                view.showErrorMessage(resp.exception.message ?: "unknown exception")
            }
        }
    }
}