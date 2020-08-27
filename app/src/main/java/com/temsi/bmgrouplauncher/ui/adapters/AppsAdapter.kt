package com.temsi.bmgrouplauncher.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.temsi.bmgrouplauncher.R
import com.temsi.bmgrouplauncher.model.AppInfo

class AppsAdapter(var listOfApp: ArrayList<AppInfo>):
    RecyclerView.Adapter<AppsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listOfApp.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = listOfApp[position]
        holder.bind(app)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val iconOfApp: ImageView = view.findViewById(R.id.iconOfApp)
        private val nameOfApp: TextView = view.findViewById(R.id.nameOfApp)

        init {
            view.setOnClickListener {
                val context: Context = view.context
                val launchIntent : Intent?
                        = context.packageManager
                    .getLaunchIntentForPackage(listOfApp[adapterPosition].packageName.toString())
                context.startActivity(launchIntent)
            }
        }

        fun bind(app: AppInfo) {
            iconOfApp.setImageDrawable(app.icon)
            nameOfApp.text = app.label
        }
    }
}