package com.ozyurt.armutapp.ui.ustalar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ozyurt.armutapp.data.model.KategorilerUstalarResponsItem
import com.ozyurt.armutapp.data.model.Ustalar
import com.ozyurt.armutapp.databinding.CardviewHizmetlerBinding
import com.ozyurt.armutapp.databinding.CardviewUstalarBinding
import com.ozyurt.armutapp.util.GlideUtil
import com.ozyurt.armutapp.util.OnItemClickListener

class UstalarAdaptor(
    var ustalar: List<Ustalar>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<UstalarAdaptor.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: CardviewUstalarBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CardviewUstalarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                txtUstalarIsim.text = ustalar[position].UstaAdi
                var imageUrl = ustalar.get(position).Fotografi as String
                GlideUtil.resmiIndirGoster(imageUstalar.context, imageUrl, imageUstalar)
                ustalarCardView.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return ustalar.size
    }


    fun setData(urunListFiltre:List<Ustalar>) {
        ustalar =urunListFiltre
    }

}