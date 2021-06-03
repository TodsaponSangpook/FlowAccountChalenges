package com.todsapon.flowaccountchalenges.presentation.categories.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todsapon.flowaccountchalenges.databinding.ItemCategoriesBinding
import com.todsapon.flowaccountchalenges.domain.model.Category

class CategoriesViewHolder(
    private val binding: ItemCategoriesBinding,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ): CategoriesViewHolder {
            val view =
                ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoriesViewHolder(view, onItemClicked)
        }
    }

    fun bind(data: Category, index: Int) {
        binding.categoryNameTextView.text = data.name

        if (data.children == null) {
            binding.arrowImageView.visibility = View.GONE
        } else {
            binding.arrowImageView.visibility = View.VISIBLE
        }

        itemView.setOnClickListener {
            data.children?.let {
                onItemClicked.invoke(index)
            }
        }
    }
}