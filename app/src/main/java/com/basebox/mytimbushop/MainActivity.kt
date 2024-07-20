package com.basebox.mytimbushop

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.basebox.mytimbushop.databinding.ActivityMainBinding
import com.basebox.mytimbushop.ui.viewmodels.NetworkViewModel
import com.basebox.mytimbushop.util.NetworkChecker
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity()  {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var networkHelper: NetworkChecker
    private lateinit var networkViewModel: NetworkViewModel
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(_binding.root)
//        drawerLayout = binding.

//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//        navView.setNavigationItemSelectedListener(this)
        val navHost = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
        val bottomNav = binding.bottomNav
        bottomNav.setupWithNavController(navController)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//
//        // Set up the navigation drawer
//        NavigationUI.setupWithNavController(navView, navController)


        networkHelper = NetworkChecker(this)
        networkViewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)

        val errorScreen: View = findViewById(R.id.errorScreen)
        val mainContent: View = findViewById(R.id.mainContent)

        networkViewModel.isNetworkAvailable.observe(this, Observer { isAvailable ->
            if (isAvailable) {
                errorScreen.visibility = View.GONE
                mainContent.visibility = View.VISIBLE
            } else {
                errorScreen.visibility = View.VISIBLE
                mainContent.visibility = View.GONE
            }
        })

//        if (savedInstanceState == null) {
//            navController.navigate(R.id.action_homeFragment_self)
//            navView.setCheckedItem(R.id.homeFragment)
//        }
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.homeFragment-> {
//                findNavController(R.id.main).navigate(R.id.action_homeFragment_self)
//            }
//            // Add more cases for other fragments
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                drawerLayout.closeDrawer(GravityCompat.START)
//            } else {
//                drawerLayout.openDrawer(GravityCompat.START)
//            }
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}