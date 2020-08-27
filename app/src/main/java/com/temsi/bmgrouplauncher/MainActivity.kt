package com.temsi.bmgrouplauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.temsi.bmgrouplauncher.model.AppInfo
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), FragmentsCallback {

    lateinit var listOfAllApp: MutableList<AppInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_screen)

        val job = Job()
        CoroutineScope(Dispatchers.IO + job).launch {
            listOfAllApp = getAppList() as MutableList<AppInfo>
            withContext(Dispatchers.Main) {
                setContentView(R.layout.activity_main)
            }
        }
    }

    private fun getAppList(): List<AppInfo> {
        val list: MutableList<AppInfo> = arrayListOf()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val allApps = packageManager.queryIntentActivities(intent, 0)
        var i = 0
        allApps.forEach { ri: ResolveInfo ->
            val app = AppInfo(
                i, label = ri.loadLabel(packageManager),
                packageName = ri.activityInfo.packageName,
                icon = ri.activityInfo.loadIcon(packageManager)
            )
            i++
            list.add(app)
        }
        return list
    }

    //Обновление списка избранных
    override fun updateAppsList(list: ArrayList<Int>) {
        list.forEach { i: Int ->
            listOfAllApp.forEach { if (!it.isFavorite && i == it.appsIndex) it.isFavorite = true }
        }
    }

}