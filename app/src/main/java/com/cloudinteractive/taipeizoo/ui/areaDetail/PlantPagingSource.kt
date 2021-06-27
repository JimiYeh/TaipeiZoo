package com.cloudinteractive.taipeizoo.ui.areaDetail

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cloudinteractive.taipeizoo.model.plant.GetPlantListResp
import com.cloudinteractive.taipeizoo.network.ApiResponse
import com.cloudinteractive.taipeizoo.repository.ZooRepository
import java.lang.RuntimeException

class PlantPagingSource(private val keyword: String, private val repository: ZooRepository) :
    PagingSource<Int, GetPlantListResp.Result.Plant>() {

    override fun getRefreshKey(state: PagingState<Int, GetPlantListResp.Result.Plant>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetPlantListResp.Result.Plant> {

        val offset = params.key ?: 0

        val resp = repository.fetchPlantList(keyword, offset)
        when (resp) {
            is ApiResponse.ApiSuccess<GetPlantListResp> -> {
                // 檢查是否還有位完資料
                resp.data?.result?.plants?.let { plants ->
                    return LoadResult.Page(
                        data = plants,
                        prevKey = null,
                        nextKey = if (offset + plants.size < resp.data.result.count) offset + plants.size else null,
                    )
                } ?: return LoadResult.Page(
                    data = listOf(),
                    prevKey = null,
                    nextKey = null,
                )
            }

            is ApiResponse.ApiError -> {
                return LoadResult.Error(RuntimeException("Api failed"))
            }

            is ApiResponse.ApiException -> {
                return LoadResult.Error(resp.exception)
            }
        }
    }
}