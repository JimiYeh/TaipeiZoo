package com.cloudinteractive.taipeizoo.ui.areaDetail

import androidx.paging.PagingData
import com.cloudinteractive.taipeizoo.model.plant.GetPlantListResp

class AreaDetailContract {
    interface View {
        fun showLoading(enabled: Boolean)
        fun showErrorMessage(message: String)
        suspend fun updatePlants(pagingData: PagingData<GetPlantListResp.Result.Plant>)
    }

    interface Presenter {
        suspend fun fetchPlantList()
    }
}