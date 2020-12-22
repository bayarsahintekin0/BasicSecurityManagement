package com.bayarsahintekin.basicsecuritymanagement.update

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bayarsahintekin.basicsecuritymanagement.R
import com.bayarsahintekin.basicsecuritymanagement.model.AssetModel

class UpdateAdapter (private val itemClicked: (item: AssetModel) -> Unit) : RecyclerView.Adapter<UpdateAdapter.ViewHolder>() {

    private var list :ArrayList<AssetModel> = arrayListOf()
    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_update_items,
                parent,
                false
            )
        ).apply { itemView.setOnClickListener { itemClicked(list[adapterPosition]) } }
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
        private val assetHandle = itemView.findViewById<TextView>(R.id.tvUpdateAssetHandle)
        fun bind(asset : AssetModel) {
            appTag.text = asset.aPPTAG
            batch.text = asset.batchAsset
            extInfo.text = asset.extInfo
            assetHandle.text = asset.assetHandle
        }
    }
}