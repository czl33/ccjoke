package com.newczl.ccjoke.ui.sofa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.newczl.ccjoke.R
import com.newczl.libnavannotation.FragmentDestination

@FragmentDestination(pageUrl = "main/tabs/sofa")
class SofaFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sofa, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        return root
    }
}