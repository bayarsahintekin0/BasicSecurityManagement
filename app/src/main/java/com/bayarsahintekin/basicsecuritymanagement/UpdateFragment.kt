package com.bayarsahintekin.basicsecuritymanagement

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.huawei.security.hwassetmanager.HwAssetManager

class UpdateFragment :Fragment() {
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var basicStorageManagementHelper : BasicStorageManagementHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_update, container, false)

        val batchAsset = root.findViewById<EditText>(R.id.etUpdateBatchasset)
        val aeadAsset= root.findViewById<EditText>(R.id.etUpdateAeadasset)
        val extraInfo = root.findViewById<EditText>(R.id.etUpdateExtraEnformation)
        val aLimit = root.findViewById<EditText>(R.id.etUpdateAuthenticationLimit)
        val sLimit = root.findViewById<EditText>(R.id.etUpdateSynchronizationLimit)
        val accessLimit = root.findViewById<EditText>(R.id.etUpdateAccessLimit)
        val updateButton = root.findViewById<Button>(R.id.btnUpdate)

        sharedPreferences = requireContext().getSharedPreferences("assetHandler", Context.MODE_PRIVATE)
        basicStorageManagementHelper = BasicStorageManagementHelper(requireContext())

        updateButton.setOnClickListener {
            val bundle = Bundle()

            bundle.apply {
                putString(HwAssetManager.BUNDLE_ASSETHANDLE,sharedPreferences.getString("asset_handle",""))
                putString(HwAssetManager.BUNDLE_APPTAG,"com.bayarsahintekin.basicsecuritymanagement")
                putString(HwAssetManager.BUNDLE_BATCHASSET,batchAsset.text.toString())
                putString(HwAssetManager.BUNDLE_AEADASSET,aeadAsset.text.toString())
                putString(HwAssetManager.BUNDLE_EXTINFO,extraInfo.text.toString())
                putInt(HwAssetManager.BUNDLE_ASSETTYPE, HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
                putInt(HwAssetManager.BUNDLE_AUTHENTICATELIMITATION,aLimit.text.toString().toInt())
                putInt(HwAssetManager.BUNDLE_SYNCLIMITATION,sLimit.text.toString().toInt())
                putInt(HwAssetManager.BUNDLE_ACCESSLIMITATION,accessLimit.text.toString().toInt())

            }

            basicStorageManagementHelper.updateData(bundle)

        }
        return root
    }
}