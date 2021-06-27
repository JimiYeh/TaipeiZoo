package com.cloudinteractive.taipeizoo.ui.areaDetail

import androidx.paging.PagingData
import com.cloudinteractive.taipeizoo.model.plant.GetPlantListResp

class AreaDetailContract {
    interface View {
        fun showLoading()
        fun showErrorMessage(message: String)
        fun updatePlants(pagingData: PagingData<GetPlantListResp.Result.Plant>)
    }

    interface Presenter {
        suspend fun fetchPlantList()
    }
}