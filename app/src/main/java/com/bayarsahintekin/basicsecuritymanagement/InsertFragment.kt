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


        val appTag = root.findViewById<EditText>(R.id.etInsertAppTag)
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
                putString(HwAssetManager.BUNDLE_APPTAG,appTag.text.toString())
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