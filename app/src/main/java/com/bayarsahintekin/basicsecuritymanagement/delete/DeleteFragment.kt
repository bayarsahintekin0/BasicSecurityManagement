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

package com.bayarsahintekin.basicsecuritymanagement.delete

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bayarsahintekin.basicsecuritymanagement.BasicStorageManagementHelper
import com.bayarsahintekin.basicsecuritymanagement.R
import com.huawei.security.hwassetmanager.HwAssetManager

class DeleteFragment :Fragment() {
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var basicStorageManagementHelper: BasicStorageManagementHelper
    private lateinit var adapter :DeleteAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_delete, container, false)

        sharedPreferences = requireContext().getSharedPreferences("assetHandler", Context.MODE_PRIVATE)
        basicStorageManagementHelper = BasicStorageManagementHelper(requireContext())

        val recyclerView = root.findViewById<RecyclerView>(R.id.rvDelete)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        adapter = DeleteAdapter(itemClicked = {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Delete")
            builder.setMessage("Are you sure?")
            builder.setNegativeButton("No", null)
            builder.setPositiveButton("Yes") { dialogInterface, i ->

                val bundle = Bundle()
                bundle.apply {
                    putString(HwAssetManager.BUNDLE_APPTAG,"com.bayarsahintekin.basicsecuritymanagement")
                    putString(HwAssetManager.BUNDLE_ASSETHANDLE,it.assetHandle)
                    putInt(HwAssetManager.BUNDLE_ASSETTYPE, HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
                }
                basicStorageManagementHelper.deleteData(bundle){
                    refreshList()
                }
            }
            builder.show()
        })
        recyclerView.adapter = adapter

        val bundle = Bundle()
        bundle.apply {
            putString(HwAssetManager.BUNDLE_APPTAG,"com.bayarsahintekin.basicsecuritymanagement")
            putString(HwAssetManager.BUNDLE_ASSETHANDLE,sharedPreferences.getString("asset_handle",""))
            putInt(HwAssetManager.BUNDLE_ASSETTYPE, HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
            putInt(HwAssetManager.BUNDLE_SELECT_MODE, HwAssetManager.SELECT_FROM_BEGIN)
        }
        adapter.setList(basicStorageManagementHelper.selectData(bundle))

        return root
    }

    private fun refreshList() {
        val bundle = Bundle()
        bundle.apply {
            putString(HwAssetManager.BUNDLE_APPTAG,"com.bayarsahintekin.basicsecuritymanagement")
            putString(HwAssetManager.BUNDLE_ASSETHANDLE,sharedPreferences.getString("asset_handle",""))
            putInt(HwAssetManager.BUNDLE_ASSETTYPE, HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
            putInt(HwAssetManager.BUNDLE_SELECT_MODE, HwAssetManager.SELECT_FROM_BEGIN)
        }
        adapter.setList(basicStorageManagementHelper.selectData(bundle))
    }
}