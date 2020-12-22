package com.bayarsahintekin.basicsecuritymanagement

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.bayarsahintekin.basicsecuritymanagement.model.AssetModel
import com.google.gson.Gson
import com.huawei.security.hwassetmanager.HwAssetManager

/**
 * Every insert api returns asset_handler (encrypted key)
 * You need to store it for getting specific data from storage
 */
class BasicStorageManagementHelper() {
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var context: Context
    private var demoAssetHandle = ""
    private val hwAssetManager = HwAssetManager.getInstance()
    constructor(context: Context) :this(){
        this.context = context
        sharedPreferences = context.getSharedPreferences("assetHandler",Context.MODE_PRIVATE)
    }


    fun insertData(bundle: Bundle){
        val result  = hwAssetManager.assetInsert(context, bundle)
        if (result.resultCode == HwAssetManager.SUCCESS) {
            sharedPreferences
                .edit()
                .putString("asset_handle",result.resultInfo[0])
                .apply()
            Toast.makeText(context,"Your asset successfully inserted: " + result.resultInfo[0], Toast.LENGTH_SHORT).show()
        }else
            onFailure(result,"Asset Insert")
    }

    fun updateData (bundle: Bundle,block :() -> Unit) {
        val result  = hwAssetManager.assetInsert(context, bundle)
        if (result.resultCode == HwAssetManager.SUCCESS) {
            sharedPreferences
                    .edit()
                    .putString("asset_handle",result.resultInfo[0])
                    .apply()
            Toast.makeText(context,"Your asset successfully updated: " + result.resultInfo[0], Toast.LENGTH_SHORT).show()
            block()
        }else
            onFailure(result,"Asset Insert")
    }

    fun deleteData(bundle: Bundle,block: () -> Unit) {
        val result = hwAssetManager.assetDelete(context,bundle)
         if (result.resultCode == HwAssetManager.SUCCESS){
           block()
        }else {
             onFailure(result, "Asset Delete")
             Toast.makeText(context,"Your asset delete is failure " , Toast.LENGTH_SHORT).show()
        }
    }

    fun selectData(bundle: Bundle) :ArrayList<AssetModel>{
        val result = hwAssetManager.assetSelect(context,bundle)
        val gson = Gson()
        val list :ArrayList<AssetModel> = arrayListOf()
        for (i in result.resultInfo){
            list.add(gson.fromJson(i,AssetModel::class.java))
        }

        return list
    }

    private fun onFailure(result : HwAssetManager.AssetResult,operationTitle :String) {
        when(result.resultCode) {
            HwAssetManager.ERROR_CODE_INVALID_ARGUMENT -> {
                Log.e(operationTitle,"Invalid Arguments"+" errorCode: " + HwAssetManager.ERROR_CODE_INVALID_ARGUMENT )
                Toast.makeText(context,"Invalid Arguments Error ", Toast.LENGTH_SHORT).show()
            }
            HwAssetManager.ERROR_CODE_PERMISSION_DENIED -> {
                Log.e(operationTitle,"Permission Denied" +" errorCode: " + HwAssetManager.ERROR_CODE_PERMISSION_DENIED )
                Toast.makeText(context,"Invalid Arguments Error ", Toast.LENGTH_SHORT).show()
            }
            HwAssetManager.ERROR_CODE_UNINITIALIZED -> {
                Log.e(operationTitle,"Initialization error" +" errorCode: " + HwAssetManager.ERROR_CODE_UNINITIALIZED )
                Toast.makeText(context,"Initialization Error ", Toast.LENGTH_SHORT).show()
            }
            HwAssetManager.ERROR_CODE_DATABASE_ERROR -> {
                Log.e(operationTitle,"Database operation error" +" errorCode: " + HwAssetManager.ERROR_CODE_DATABASE_ERROR )
                Toast.makeText(context,"Database Operation Error ", Toast.LENGTH_SHORT).show()
            }
            HwAssetManager.ERROR_CODE_UNSECURED_ENVIRONMENT -> {
                Log.e(operationTitle,"unsecured environment error" +" errorCode: " + HwAssetManager.ERROR_CODE_UNSECURED_ENVIRONMENT )
                Toast.makeText(context,"Unsecured Environment Error ", Toast.LENGTH_SHORT).show()
            }
            HwAssetManager.ERROR_CODE_SYSTEM_ERROR -> {
                Log.e(operationTitle,"System error" +" errorCode: " + HwAssetManager.ERROR_CODE_SYSTEM_ERROR )
                Toast.makeText(context,"System Error ", Toast.LENGTH_SHORT).show()
            }
        }
    }

}