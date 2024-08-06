package com.example.app_name.view.activities

import android.os.Bundle
import android.view.KeyEvent
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.app_name.R
import com.example.app_name.view.activities.base.BaseActivity

class MainActivity : BaseActivity() {

    lateinit var navController: NavController
    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment?
        val inflater = navHostFragment?.navController?.navInflater
        val graph = inflater?.inflate(R.navigation.mobile_navigation)

        graph?.startDestination = R.id.navigation_user

        if (graph != null) {
            navHostFragment?.navController?.graph = graph
        }



        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_user, R.id.navigation_user_detail
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?) = if (keyCode == KeyEvent.KEYCODE_BACK) {
        val currentDestination = navController.currentDestination
        if (currentDestination != null && currentDestination.id == R.id.navigation_user) {
            moveTaskToBack(true)
            true
        } else {
            super.onBackPressed()
            false
        }
    } else false

    override fun onBackPressed() = Unit

}