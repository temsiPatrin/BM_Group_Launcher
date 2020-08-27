package com.temsi.bmgrouplauncher.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.temsi.bmgrouplauncher.R
import com.temsi.bmgrouplauncher.model.AppInfo
import com.temsi.bmgrouplauncher.ui.AddingScreen.OnItemClickListener

class AppsFlagAdapter(
    var listOfApp: ArrayList<AppInfo>,
    private val onItemClickListener: OnItemClickListener
):
    RecyclerView.Adapter<AppsFlagAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app_with_flag, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =listOfApp.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = listOfApp[position]
        holder.bind(app)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val iconOfApp: ImageView = view.findViewById(R.id.iconOfAppF)
        private val nameOfApp: TextView = view.findViewById(R.id.nameOfAppF)
        private val checkBox: CheckBox = view.findViewById(R.id.checkBox)

        init {
            view.setOnClickListener {
                if (checkBox.isChecked) {
                    onItemClickListener.removeToFavorite(listOfApp[adapterPosition])
                    checkBox.isChecked = false
                } else {
                    onItemClickListener.addToFavorite(listOfApp[adapterPosition])
                    checkBox.isChecked = true
                }
            }
        }

        fun bind(app: AppInfo) {
            iconOfApp.setImageDrawable(app.icon)
            nameOfApp.text = app.label
            checkBox.isChecked = false
        }
    }
}