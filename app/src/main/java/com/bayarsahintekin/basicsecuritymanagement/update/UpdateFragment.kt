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

package com.bayarsahintekin.basicsecuritymanagement.update

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bayarsahintekin.basicsecuritymanagement.BasicStorageManagementHelper
import com.bayarsahintekin.basicsecuritymanagement.R
import com.huawei.security.hwassetmanager.HwAssetManager

class UpdateFragment :Fragment() {
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var basicStorageManagementHelper : BasicStorageManagementHelper
    private lateinit var adapter:UpdateAdapter
    private var selectedAssetHandler = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_update, container, false)

        sharedPreferences = requireContext().getSharedPreferences("assetHandler", Context.MODE_PRIVATE)
        basicStorageManagementHelper =
            BasicStorageManagementHelper(
                requireContext()
            )

        val batchAsset = root.findViewById<EditText>(R.id.etUpdateBatchasset)
        val aeadAsset= root.findViewById<EditText>(R.id.etUpdateAeadasset)
        val extraInfo = root.findViewById<EditText>(R.id.etUpdateExtraEnformation)
        val aLimit = root.findViewById<EditText>(R.id.etUpdateAuthenticationLimit)
        val sLimit = root.findViewById<EditText>(R.id.etUpdateSynchronizationLimit)
        val accessLimit = root.findViewById<EditText>(R.id.etUpdateAccessLimit)
        val updateButton = root.findViewById<Button>(R.id.btnUpdate)

        val scroolView :ScrollView = root.findViewById(R.id.svUpdateForm)

        val recyclerView :RecyclerView = root.findViewById(R.id.rvUpdate)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        adapter =
            UpdateAdapter(itemClicked = {
                batchAsset.setText(it.batchAsset)
                extraInfo.setText(it.extInfo)
                aLimit.setText(it.authenticateLimitation.toString())
                sLimit.setText(it.syncLimitation.toString())
                accessLimit.setText(it.accessLimitation.toString())
                selectedAssetHandler = it.assetHandle.toString()

                recyclerView.visibility = View.GONE
                scroolView.visibility = View.VISIBLE

            })
        recyclerView.adapter = adapter

        getAssetList()






        updateButton.setOnClickListener {
            val bundle = Bundle()

            bundle.apply {
                putString(HwAssetManager.BUNDLE_ASSETHANDLE,selectedAssetHandler)
                putString(HwAssetManager.BUNDLE_APPTAG,"com.bayarsahintekin.basicsecuritymanagement")
                putString(HwAssetManager.BUNDLE_BATCHASSET,batchAsset.text.toString())
                putString(HwAssetManager.BUNDLE_AEADASSET,aeadAsset.text.toString())
                putString(HwAssetManager.BUNDLE_EXTINFO,extraInfo.text.toString())
                putInt(HwAssetManager.BUNDLE_ASSETTYPE, HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
                putInt(HwAssetManager.BUNDLE_AUTHENTICATELIMITATION,aLimit.text.toString().toInt())
                putInt(HwAssetManager.BUNDLE_SYNCLIMITATION,sLimit.text.toString().toInt())
                putInt(HwAssetManager.BUNDLE_ACCESSLIMITATION,accessLimit.text.toString().toInt())

            }

            basicStorageManagementHelper.updateData(bundle) {
                recyclerView.visibility = View.VISIBLE
                scroolView.visibility = View.GONE
                getAssetList()
            }

        }
        return root
    }

    private fun getAssetList(){
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