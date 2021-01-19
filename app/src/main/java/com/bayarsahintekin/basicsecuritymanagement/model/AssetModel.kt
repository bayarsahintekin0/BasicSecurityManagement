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