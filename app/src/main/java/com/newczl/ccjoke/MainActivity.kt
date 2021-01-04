package com.newczl.ccjoke

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.newczl.ccjoke.utils.AppConfig
import com.newczl.ccjoke.utils.NavGraphBuilder
import com.newczl.libnavannotation.ActivityDestination
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val navController by lazy { this.findNavController(R.id.navFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = supportFragmentManager.findFragmentById(R.id.navFragment)

        NavigationUI.setupWithNavController(bottomBar,navController)
        NavGraphBuilder.build(navController,this,fragment!!.id)
        bottomBar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.i("tag","item.itemId IS ${item.itemId}")
        navController.navigate(item.itemId)
        return item.title.isNotEmpty()
    }
}