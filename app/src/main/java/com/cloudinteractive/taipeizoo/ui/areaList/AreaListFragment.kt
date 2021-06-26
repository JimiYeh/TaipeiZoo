package com.cloudinteractive.taipeizoo.ui.areaList

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            presenter.fetchAreaList()
        }
    }

    override fun showArea(areaList: List<GetAreaListResp.Result.Area>) {

    }

    override fun showErrorMessage(message: String) {

    }
}