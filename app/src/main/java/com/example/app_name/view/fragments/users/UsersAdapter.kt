package com.example.app_name.view.fragments.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_name.R
import com.example.app_name.models.usersModel.AllUserResponseItem
import kotlinx.android.synthetic.main.item_users.view.*

internal class UsersAdapter(private val onClick: (AllUserResponseItem) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.MViewHolder>() {

    private val differ = AsyncListDiffer(
        this,
        DiffUtils
    )
    private var items: ArrayList<AllUserResponseItem> = arrayListOf()
        set(value) {
            field = value
            differ.submitList(ArrayList(value))
        }

    init {
        differ.submitList(items)
    }


    fun submitList(list: List<AllUserResponseItem>) {
        items.addAll(list)

        differ.submitList(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return MViewHolder(view)
    }

    override fun getItemCount() = differ.currentList.count()

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        differ.currentList[position].let {
            holder.bind(it)
        }
    }


    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: AllUserResponseItem) = itemView.run {
            item_user_name.text = model.login

            Glide.with(item_user_image_view).load(model.avatar_url).into(item_user_image_view)

            setOnClickListener {
                onClick.invoke(
                    model
                )
            }

        }
    }

    object DiffUtils : DiffUtil.ItemCallback<AllUserResponseItem>() {
        override fun areItemsTheSame(
            oldItem: AllUserResponseItem, newItem: AllUserResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AllUserResponseItem, newItem: AllUserResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
