package com.cloudinteractive.taipeizoo.ui.areaDetail

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.repository.ZooRepository
import kotlinx.coroutines.flow.collectLatest

class AreaDetailPresenter(
    private val area: GetAreaListResp.Result.Area,
    private val view: AreaDetailContract.View
) : AreaDetailContract.Presenter {

    private val flow = Pager(
        PagingConfig(
            pageSize = 20,
            prefetchDistance = 1,
            enablePlaceholders = false
        )
    ) {
        PlantPagingSource(
            keyword = area.eName,
            ZooRepository()
        )
    }.flow

    override suspend fun fetchPlantList() {
        flow.collectLatest {
            view.updatePlants(it)
        }
    }
}