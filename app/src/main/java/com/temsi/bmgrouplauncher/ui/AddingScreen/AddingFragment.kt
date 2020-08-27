package com.temsi.bmgrouplauncher.ui.AddingScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.temsi.bmgrouplauncher.MainActivity
import com.temsi.bmgrouplauncher.R
import com.temsi.bmgrouplauncher.model.AppInfo
import com.temsi.bmgrouplauncher.ui.adapters.AppsFlagAdapter
import com.temsi.bmgrouplauncher.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_adding.*
import kotlinx.coroutines.*

class AddingFragment : BaseFragment(), OnItemClickListener, AddDialog.DialogOnClickListener {

    private val listIndexApp: MutableList<Int> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adding, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerAdding)
        recyclerView.layoutManager =
            GridLayoutManager(
                activity?.applicationContext, 4,
                LinearLayoutManager.VERTICAL, false
            )
        val list: ArrayList<AppInfo> = (activity as MainActivity).listOfAllApp as ArrayList<AppInfo>
        recyclerView.adapter =
            AppsFlagAdapter(list.filter { !it.isFavorite } as ArrayList<AppInfo>,
                this)

        return view
    }

    override fun addToFavorite(app: AppInfo) {
        listIndexApp.add(app.appsIndex)
    }

    override fun removeToFavorite(app: AppInfo) {
        listIndexApp.remove(app.appsIndex)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGoToHome.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = AddDialog()
        dialog.setTargetFragment(this, 1)
        dialog.show(parentFragmentManager, "MyDialog")
    }

    override fun onDialogPositiveClickListener() {
        val job = Job()
        CoroutineScope(Dispatchers.IO + job).launch {
            callback?.updateAppsList(listIndexApp as ArrayList<Int>)
            withContext(Dispatchers.Main) {
                goToHome()
            }
        }
    }

    private fun goToHome() {
        findNavController(this).navigate(R.id.action_addingFragment_to_homeFragment)
    }
}