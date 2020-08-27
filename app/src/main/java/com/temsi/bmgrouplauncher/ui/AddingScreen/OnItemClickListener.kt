package com.temsi.bmgrouplauncher.ui.AddingScreen

import com.temsi.bmgrouplauncher.model.AppInfo

interface OnItemClickListener {
    fun addToFavorite(app: AppInfo)
    fun removeToFavorite(app: AppInfo)
}