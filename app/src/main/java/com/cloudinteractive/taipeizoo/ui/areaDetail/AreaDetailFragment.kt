package com.cloudinteractive.taipeizoo.ui.areaDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.bumptech.glide.Glide
import com.cloudinteractive.taipeizoo.R
import com.cloudinteractive.taipeizoo.databinding.FragmentAreaDetailBinding
import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.model.plant.GetPlantListResp
import com.cloudinteractive.taipeizoo.ui.plantDetail.PlantDetailFragment
import com.cloudinteractive.taipeizoo.ui.viewBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

class AreaDetailFragment private constructor() : Fragment(R.layout.fragment_area_detail),
    AreaDetailContract.View {

    companion object {

        const val BUNDLE_AREA = "BUNDLE_AREA"

        fun newInstance(area: GetAreaListResp.Result.Area) = AreaDetailFragment().apply {
            val bundle = Bundle().apply {
                putParcelable(BUNDLE_AREA, area)
            }
            arguments = bundle
        }
    }

    private val binding by viewBinding(FragmentAreaDetailBinding::bind)

    private lateinit var area: GetAreaListResp.Result.Area
    private val presenter: AreaDetailContract.Presenter by lazy { AreaDetailPresenter(area, this) }

    @ObsoleteCoroutinesApi
    private lateinit var plantListEpoxyController: PlantListEpoxyController

    @ObsoleteCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        area = arguments?.getParcelable(BUNDLE_AREA)
            ?: throw IllegalArgumentException("null area")

        updateToolbar()

        Glide.with(binding.appbar.ivBanner)
        binding.appbar.apply {
            Glide.with(ivBanner)
                .load(area.ePicURL)
                .centerCrop()
                .into(ivBanner)

            tvInfo.text = area.eInfo
            tvMemo.text = area.eMemo
            tvCategory.text = area.eCategory

            tvOpenWebpage.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(area.eURL)))
            }
        }

        plantListEpoxyController = PlantListEpoxyController(this::onPlantItemClick)
        plantListEpoxyController.addLoadStateListener { loadStates ->
            showLoading(loadStates.refresh is LoadState.Loading)
        }
        binding.ervPlantList.setController(plantListEpoxyController)


        lifecycleScope.launch {
            presenter.fetchPlantList()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            updateToolbar()
        }
    }


    override fun showLoading(enabled: Boolean) {
        binding.pbLoading.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun showErrorMessage(message: String) {

    }

    @ObsoleteCoroutinesApi
    override suspend fun updatePlants(pagingData: PagingData<GetPlantListResp.Result.Plant>) {
        plantListEpoxyController.submitData(pagingData)
    }


    private fun updateToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = area.eName
        }
    }

    private fun onPlantItemClick(plant: GetPlantListResp.Result.Plant) {
        parentFragmentManager.commit {
            add(R.id.flContainer, PlantDetailFragment.newInstance(plant), PlantDetailFragment::class.simpleName)
            hide(this@AreaDetailFragment)
            setReorderingAllowed(false)
            addToBackStack(null)
        }
    }
}