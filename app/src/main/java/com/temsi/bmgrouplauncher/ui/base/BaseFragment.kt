package com.temsi.bmgrouplauncher.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.temsi.bmgrouplauncher.FragmentsCallback

open class BaseFragment: Fragment(){
    var callback: FragmentsCallback? =null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is FragmentsCallback) {
            this.callback = activity as FragmentsCallback
        }
    }
    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}