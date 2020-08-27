package com.temsi.bmgrouplauncher.ui.HomeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.temsi.bmgrouplauncher.MainActivity
import com.temsi.bmgrouplauncher.R
import com.temsi.bmgrouplauncher.model.AppInfo
import com.temsi.bmgrouplauncher.ui.adapters.AppsAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerHome)
        recyclerView.layoutManager =
            GridLayoutManager(activity?.applicationContext, 4,
                LinearLayoutManager.VERTICAL, false)
        val list: ArrayList<AppInfo> = (activity as MainActivity).listOfAllApp as ArrayList<AppInfo>
        recyclerView.adapter =
            AppsAdapter(list.filter { it.isFavorite } as ArrayList<AppInfo>)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGoToAll.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_homeFragment_to_allAppsFragment) }
        btnGoToAdding.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_homeFragment_to_addingFragment) }
    }


}