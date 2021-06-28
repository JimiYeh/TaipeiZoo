package com.cloudinteractive.taipeizoo.ui.areaDetail

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.bumptech.glide.Glide
import com.cloudinteractive.taipeizoo.R
import com.cloudinteractive.taipeizoo.databinding.ItemPlantListBinding
import com.cloudinteractive.taipeizoo.epoxy.ViewBindingKotlinModel
import com.cloudinteractive.taipeizoo.model.plant.GetPlantListResp
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class PlantListEpoxyController(private val onItemClick: (GetPlantListResp.Result.Plant) -> Unit) :
    PagingDataEpoxyController<GetPlantListResp.Result.Plant>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetPlantListResp.Result.Plant?
    ): EpoxyModel<*> {
        return PlantListItemEpoxyModel(
            item!!,
            onItemClick
        ).id(item.id)
    }


    class PlantListItemEpoxyModel(
        private val plant: GetPlantListResp.Result.Plant,
        private val onClick: (GetPlantListResp.Result.Plant) -> Unit
    ) : ViewBindingKotlinModel<ItemPlantListBinding>(R.layout.item_plant_list) {
        override fun ItemPlantListBinding.bind() {
            Glide.with(ivPhoto)
                .load(plant.fPic01URL)
                .centerCrop()
                .into(ivPhoto)

            tvName.text = plant.fNameCh
            tvAlsoKnown.text = plant.fAlsoKnown

            clContainer.setOnClickListener {
                onClick.invoke(plant)
            }
        }
    }
}