package com.bayarsahintekin.basicsecuritymanagement

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.huawei.security.hwassetmanager.HwAssetManager

class SelectFragment :Fragment() {
    private lateinit var sharedPreferences :SharedPreferences
    private lateinit var basicStorageManagementHelper :BasicStorageManagementHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select, container, false)

        sharedPreferences = requireContext().getSharedPreferences("assetHandler", Context.MODE_PRIVATE)
        basicStorageManagementHelper = BasicStorageManagementHelper(requireContext())
        val selectButton = root.findViewById<Button>(R.id.btnSelect)
        val appTag = root.findViewById<EditText>(R.id.etSelectAppTag)
        val text = root.findViewById<TextView>(R.id.tvSelect)
        selectButton.setOnClickListener {
            val bundle = Bundle()
            bundle.apply {
                putString(HwAssetManager.BUNDLE_APPTAG,appTag.text.toString())
                putString(HwAssetManager.BUNDLE_ASSETHANDLE,sharedPreferences.getString("asset_handle",""))
                putInt(HwAssetManager.BUNDLE_ASSETTYPE, HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
                putInt(HwAssetManager.BUNDLE_SELECT_MODE, HwAssetManager.SELECT_CONTINUE)
            }
            val assetList = basicStorageManagementHelper.selectData(bundle)
            if (assetList.isNotEmpty())
                text.text = "First Asset :" + assetList[0].toString()
        }

        return root
    }
}