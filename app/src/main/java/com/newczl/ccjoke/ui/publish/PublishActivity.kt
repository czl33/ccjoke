package com.newczl.ccjoke.ui.publish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.NavigationUI
import com.newczl.ccjoke.R
import com.newczl.ccjoke.utils.NavGraphBuilder
import com.newczl.libnavannotation.ActivityDestination
import kotlinx.android.synthetic.main.activity_main.*

@ActivityDestination(pageUrl = "main/tabs/publish")
class PublishActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public)
    }
}