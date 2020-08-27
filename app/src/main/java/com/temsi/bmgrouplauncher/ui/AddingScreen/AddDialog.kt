package com.temsi.bmgrouplauncher.ui.AddingScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.temsi.bmgrouplauncher.R

class AddDialog : DialogFragment(){
    var mListener: DialogOnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_fragment, container)
        val btnYes : Button = view.findViewById(R.id.buttonYes)
        val btnNo : Button = view.findViewById(R.id.buttonNo)
        btnYes.setOnClickListener{
            mListener?.onDialogPositiveClickListener()
            dismiss()
        }
        btnNo.setOnClickListener{
            dismiss()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = targetFragment as DialogOnClickListener
    }

    interface DialogOnClickListener {
        fun onDialogPositiveClickListener()
    }
}