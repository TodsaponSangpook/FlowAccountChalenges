package com.todsapon.flowaccountchalenges.presentation.categories.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.todsapon.flowaccountchalenges.domain.model.Category

class CategoriesAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<Category, CategoriesViewHolder>(CategoriesDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder.from(parent, onItemClicked)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, position)
    }

    class CategoriesDiffCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name
        }
    }
}