package com.cloudinteractive.taipeizoo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import com.cloudinteractive.taipeizoo.R
import com.cloudinteractive.taipeizoo.ui.areaList.AreaListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        supportFragmentManager.commit {
            add(R.id.flContainer, AreaListFragment(), AreaListFragment::class.java.simpleName)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // 檢查是否在館區列表頁
            supportFragmentManager.findFragmentByTag(AreaListFragment::class.java.simpleName)?.run {
                if (isVisible) {
                    // TODO: 2021/6/26 show drawer layout
                } else
                    onBackPressed()
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}