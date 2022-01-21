package com.example.finaluri2

import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import android.view.MenuItem
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView




class MainActivity : AppCompatActivity() {

    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mainLink: String = ""



        val textView: TextView = findViewById(R.id.mainText) as TextView
        val googleButton = findViewById<Button>(R.id.googleButton)
        googleButton.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(mainLink))
            startActivity(i)
        }

        val button = findViewById<Button>(R.id.mainButton)
        button.setOnClickListener {
            val randomSachmeli = sachmelebi.random()
            println()
            val indexOfRandomSachmeli = sachmelebi.indexOf(randomSachmeli)

            sachmelebi.removeAt(indexOfRandomSachmeli)
            textView.text = randomSachmeli.name
            mainLink = randomSachmeli.link

        }
        setUpToolbar()
        navigationView = findViewById<View>(R.id.navigation_menu) as NavigationView
        navigationView!!.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_share -> {
                    val sharingIntent = Intent(Intent.ACTION_SEND)
                    sharingIntent.type = "text/plain"
                    val shareBody = "http://play.google.com/store/apps/detail?id=$packageName"
                    val shareSub = "Try now"
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                    startActivity(Intent.createChooser(sharingIntent, "Share using"))
                }
                R.id.nav_drawer1 -> {
                    sachmelebi.addAll(yvelaSachmelebi)
                    sachmelebi.removeAll(testa)
                }
                R.id.nav_drawer2 -> {
                    sachmelebi.addAll(testa)
                    sachmelebi.removeAll(yvelaSachmelebi)
                }
            }
            false
        }
    }


    fun setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        //drawerLayout.addDrawerListener(actionBarDrawerToggle!!)

        actionBarDrawerToggle!!.syncState()
    }


}