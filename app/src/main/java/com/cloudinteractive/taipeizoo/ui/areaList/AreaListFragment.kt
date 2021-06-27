package com.cloudinteractive.taipeizoo.ui.areaList

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cloudinteractive.taipeizoo.R
import com.cloudinteractive.taipeizoo.databinding.FragmentAreaListBinding
import com.cloudinteractive.taipeizoo.model.area.GetAreaListResp
import com.cloudinteractive.taipeizoo.ui.viewBinding
import kotlinx.coroutines.launch

class AreaListFragment : Fragment(R.layout.fragment_area_list), AreaListContract.View {

    private val binding: FragmentAreaListBinding by viewBinding(FragmentAreaListBinding::bind)
    private val presenter: AreaListContract.Presenter by lazy { AreaListPresenter(this) }

    private val areaListEpoxyController: AreaListEpoxyController by lazy {
        AreaListEpoxyController(this::onAreaItemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            title = getString(R.string.taipei_zoo)
        }


        binding.ervArea.setController(areaListEpoxyController)

        lifecycleScope.launch {
            presenter.fetchAreaList()
        }

    }

    override fun showLoading(enabled: Boolean) {
        binding.pbLoading.visibility = if (enabled) VISIBLE else GONE
    }

    override fun showArea(areaList: List<GetAreaListResp.Result.Area>) {
        areaListEpoxyController.setData(areaList)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }


    private fun onAreaItemClick(area: GetAreaListResp.Result.Area) {

    }
}