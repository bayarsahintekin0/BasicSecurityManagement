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

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.huawei.security.hwassetmanager.HwAssetManager

class InsertFragment :Fragment() {
    private val hwAssetManager = HwAssetManager.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_insert, container, false)

        val batchAsset = root.findViewById<EditText>(R.id.etInsertBatchasset)
        val aeadAsset= root.findViewById<EditText>(R.id.etInsertAeadasset)
        val extraInfo = root.findViewById<EditText>(R.id.etInsertExtraEnformation)
        val aLimit = root.findViewById<EditText>(R.id.etInsertAuthenticationLimit)
        val sLimit = root.findViewById<EditText>(R.id.etInsertSynchronizationLimit)
        val accessLimit = root.findViewById<EditText>(R.id.etInsertAccessLimit)
        val insertButton = root.findViewById<Button>(R.id.btnInsert)

        val basicStorageManagementHelper  = BasicStorageManagementHelper(requireContext())


        insertButton.setOnClickListener {
            val bundle = Bundle()

            bundle.apply {
                putString(HwAssetManager.BUNDLE_APPTAG,"com.bayarsahintekin.basicsecuritymanagement")
                putString(HwAssetManager.BUNDLE_BATCHASSET,batchAsset.text.toString())
                putString(HwAssetManager.BUNDLE_AEADASSET,aeadAsset.text.toString())
                putString(HwAssetManager.BUNDLE_EXTINFO,extraInfo.text.toString())
                putInt(HwAssetManager.BUNDLE_ASSETTYPE,HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
                putInt(HwAssetManager.BUNDLE_AUTHENTICATELIMITATION,aLimit.text.toString().toInt())
                putInt(HwAssetManager.BUNDLE_SYNCLIMITATION,sLimit.text.toString().toInt())
                putInt(HwAssetManager.BUNDLE_ACCESSLIMITATION,accessLimit.text.toString().toInt())

            }

           basicStorageManagementHelper.insertData(bundle)

        }

        return root
    }
}