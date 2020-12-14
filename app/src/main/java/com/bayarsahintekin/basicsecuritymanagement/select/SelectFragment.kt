package com.bayarsahintekin.basicsecuritymanagement.select

import android.content.Context
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
import com.bayarsahintekin.basicsecuritymanagement.delete.DeleteAdapter
import com.huawei.security.hwassetmanager.HwAssetManager

class SelectFragment :Fragment() {
    private lateinit var sharedPreferences :SharedPreferences
    private lateinit var basicStorageManagementHelper : BasicStorageManagementHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select, container, false)

        sharedPreferences = requireContext().getSharedPreferences("assetHandler", Context.MODE_PRIVATE)
        basicStorageManagementHelper = BasicStorageManagementHelper(requireContext())

        val recyclerView = root.findViewById<RecyclerView>(R.id.rvSelect)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        val adapter = SelectAdapter()
        recyclerView.adapter = adapter

        val bundle = Bundle()
        bundle.apply {
            putString(HwAssetManager.BUNDLE_APPTAG,"com.bayarsahintekin.basicsecuritymanagement")
            putString(HwAssetManager.BUNDLE_ASSETHANDLE,sharedPreferences.getString("asset_handle",""))
            putInt(HwAssetManager.BUNDLE_ASSETTYPE, HwAssetManager.ASSET_TYPE_USERNAME_PASSWORD)
            putInt(HwAssetManager.BUNDLE_SELECT_MODE, HwAssetManager.SELECT_FROM_BEGIN)
        }
        val assetList = basicStorageManagementHelper.selectData(bundle)
        adapter.setList(assetList)


        return root
    }
}