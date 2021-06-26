package com.cloudinteractive.taipeizoo.ui.areaList

import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp

class AreaListContract {
    interface View {
        fun showArea(areaList: List<GetAreaListResp.Result.Area>)
        fun showErrorMessage(message: String)
    }


    interface Presenter {
        suspend fun fetchAreaList()
    }
}