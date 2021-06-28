package com.cloudinteractive.taipeizoo.ui.plantDetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cloudinteractive.taipeizoo.R
import com.cloudinteractive.taipeizoo.databinding.FragmentPlantDetailBinding
import com.cloudinteractive.taipeizoo.model.plant.GetPlantListResp
import com.cloudinteractive.taipeizoo.ui.viewBinding

class PlantDetailFragment private constructor() : Fragment(R.layout.fragment_plant_detail) {

    companion object {
        const val BUNDLE_PLANT = "BUNDLE_PLANT"

        fun newInstance(plant: GetPlantListResp.Result.Plant) = PlantDetailFragment().apply {
            val bundle = Bundle().apply {
                putParcelable(BUNDLE_PLANT, plant)
            }
            arguments = bundle
        }
    }

    private val binding by viewBinding(FragmentPlantDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plant = arguments?.getParcelable<GetPlantListResp.Result.Plant>(BUNDLE_PLANT)
            ?: throw IllegalArgumentException("null plant")

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = plant.fNameCh
        }


        Glide.with(binding.ivPlant)
            .load(plant.fPic01URL)
            .centerCrop()
            .into(binding.ivPlant)

        binding.tvNameCh.text = plant.fNameCh
        binding.tvNameEn.text = plant.fNameEn
        binding.tvAlsoKnown.text = String.format(getString(R.string.plant_also_known), plant.fAlsoKnown)
        binding.tvABrief.text = String.format(getString(R.string.plant_brief), plant.fBrief)
        binding.tvFeature.text = String.format(getString(R.string.plant_function), plant.function)
        binding.tvLastUpdate.text = String.format(getString(R.string.plant_last_update), plant.fUpdate)
    }

}