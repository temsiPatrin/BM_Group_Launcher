package com.temsi.bmgrouplauncher.ui.AllAppsScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.temsi.bmgrouplauncher.MainActivity
import com.temsi.bmgrouplauncher.R
import com.temsi.bmgrouplauncher.model.AppInfo
import com.temsi.bmgrouplauncher.ui.adapters.AppsAdapter


class AllAppsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_apps, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerAllApps)
        recyclerView.layoutManager =
            GridLayoutManager(activity?.applicationContext, 4,
                LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter =
            AppsAdapter((activity as MainActivity).listOfAllApp as ArrayList<AppInfo>)

        return view
    }

}