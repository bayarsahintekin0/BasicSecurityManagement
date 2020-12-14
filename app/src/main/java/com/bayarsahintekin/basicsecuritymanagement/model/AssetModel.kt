package com.bayarsahintekin.basicsecuritymanagement.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AssetModel (
    @SerializedName("APPTAG")
    @Expose
    var aPPTAG: String? = null,

    @SerializedName("AccessLimitation")
    @Expose
    var accessLimitation: Int? = null,

    @SerializedName("AssetHandle")
    @Expose
    var assetHandle: String? = null,

    @SerializedName("AssetType")
    @Expose
    var assetType: Int? = null,

    @SerializedName("AuthenticateLimitation")
    @Expose
    var authenticateLimitation: Int? = null,

    @SerializedName("BatchAsset")
    @Expose
    var batchAsset: String? = null,

    @SerializedName("ExtInfo")
    @Expose
    var extInfo: String? = null,

    @SerializedName("SyncLimitation")
    @Expose
    var syncLimitation: Int? = null
)