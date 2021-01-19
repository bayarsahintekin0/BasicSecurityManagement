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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bayarsahintekin.basicsecuritymanagement.R
import com.bayarsahintekin.basicsecuritymanagement.model.AssetModel

class DeleteAdapter(private val itemClicked: (item: AssetModel) -> Unit) : RecyclerView.Adapter<DeleteAdapter.ViewHolder>() {

    private var list :ArrayList<AssetModel> = arrayListOf()
    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_delete_items,parent,false))
            .apply { itemView.setOnClickListener { itemClicked(list[adapterPosition]) } }
    }

    fun setList(list :ArrayList<AssetModel>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val appTag = itemView.findViewById<TextView>(R.id.tvAppTag)
        private val batch = itemView.findViewById<TextView>(R.id.tvBatchAsset)
        private val extInfo = itemView.findViewById<TextView>(R.id.tvExtraInf)
        private val assetHandle = itemView.findViewById<TextView>(R.id.tvDeleteAssetHandle)
        fun bind(asset :AssetModel) {
            appTag.text = asset.aPPTAG
            batch.text = asset.batchAsset
            extInfo.text = asset.extInfo
            assetHandle.text = asset.assetHandle
        }
    }
}