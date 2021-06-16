package com.ozyurt.armutapp.ui.kategoriler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ozyurt.armutapp.data.model.KategorilerUstalarResponsItem
import com.ozyurt.armutapp.databinding.CardviewHizmetlerBinding
import com.ozyurt.armutapp.util.GlideUtil
import com.ozyurt.armutapp.util.OnItemClickListener

class KategoriAdaptor(
        var categories: List<KategorilerUstalarResponsItem>,
        var onItemClickListener: OnItemClickListener
    ) : RecyclerView.Adapter<KategoriAdaptor.CategoryViewHolder>() {

        inner class CategoryViewHolder(val binding: CardviewHizmetlerBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val binding =
                CardviewHizmetlerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            with(holder) {
                binding.apply {
                    txtHizmetlerIsim.text = categories[position].KategoriAdi
                    var imageUrl = categories.get(position).kapakResim as String
                    GlideUtil.resmiIndirGoster(imageHizmetler.context, imageUrl, imageHizmetler)
                    hizmetlerCardView.setOnClickListener{
                        onItemClickListener.onItemClick(position)
                    }
                }
            }
        }

        override fun getItemCount(): Int {
            return categories.size
        }

    }
