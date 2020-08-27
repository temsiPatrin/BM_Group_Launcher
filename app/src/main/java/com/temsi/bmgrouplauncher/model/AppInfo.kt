package com.temsi.bmgrouplauncher.model

import android.graphics.drawable.Drawable

data class AppInfo(
    var appsIndex: Int,
    var label: CharSequence,
    var packageName: CharSequence,
    var icon: Drawable,
    var isFavorite: Boolean = false) {
}