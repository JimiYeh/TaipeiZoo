package com.cloudinteractive.taipeizoo.ui.areaList


import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.Glide
import com.cloudinteractive.taipeizoo.R
import com.cloudinteractive.taipeizoo.databinding.ItemAreaListBinding
import com.cloudinteractive.taipeizoo.epoxy.ViewBindingKotlinModel
import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp

class AreaListEpoxyController(private val onAreaItemClick: (GetAreaListResp.Result.Area) -> Unit) :
    TypedEpoxyController<List<GetAreaListResp.Result.Area>>() {
    override fun buildModels(areas: List<GetAreaListResp.Result.Area>) {
        add(areas.map {
            AreaListItemEpoxyModel(it, onAreaItemClick)
                .id(it.eNo)
        })
    }


    class AreaListItemEpoxyModel(
        private val area: GetAreaListResp.Result.Area,
        private val onAreaItemClick: (GetAreaListResp.Result.Area) -> Unit
    ) : ViewBindingKotlinModel<ItemAreaListBinding>(R.layout.item_area_list) {
        override fun ItemAreaListBinding.bind() {
            Glide.with(ivPhoto)
                .load(area.ePicURL)
                .centerCrop()
                .into(ivPhoto)

            tvName.text = area.eName
            tvInfo.text = area.eInfo
            tvMemo.text = area.eMemo

            clContainer.setOnClickListener {
                onAreaItemClick.invoke(area)
            }
        }
    }
}