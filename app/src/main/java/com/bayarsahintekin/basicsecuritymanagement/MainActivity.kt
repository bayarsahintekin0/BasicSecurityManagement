/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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