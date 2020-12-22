package com.bayarsahintekin.basicsecuritymanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bayarsahintekin.basicsecuritymanagement.delete.DeleteFragment
import com.bayarsahintekin.basicsecuritymanagement.select.SelectFragment
import com.bayarsahintekin.basicsecuritymanagement.update.UpdateFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertFragment = InsertFragment()
        val updateFragment =
            UpdateFragment()
        val selectFragment = SelectFragment()
        val deleteFragment =
            DeleteFragment()

        changeFragment(insertFragment)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> { changeFragment(insertFragment) }
                    1 -> { changeFragment(updateFragment) }
                    2 -> { changeFragment(selectFragment) }
                    3 -> { changeFragment(deleteFragment) }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}