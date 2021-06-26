package com.cloudinteractive.taipeizoo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.cloudinteractive.taipeizoo.R
import com.cloudinteractive.taipeizoo.ui.areaList.AreaListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.flContainer, AreaListFragment(), AreaListFragment::class.java.simpleName)
        }
    }
}