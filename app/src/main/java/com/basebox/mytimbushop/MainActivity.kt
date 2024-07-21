package com.basebox.mytimbushop

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.basebox.mytimbushop.databinding.ActivityMainBinding
import com.basebox.mytimbushop.ui.viewmodels.NetworkViewModel
import com.basebox.mytimbushop.util.NetworkChecker

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var networkHelper: NetworkChecker
    private lateinit var networkViewModel: NetworkViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(_binding.root)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
        val bottomNav = binding.bottomNav
        bottomNav.setupWithNavController(navController)


        networkHelper = NetworkChecker(this)
        networkViewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)

        val errorScreen: View = findViewById(R.id.errorScreen)
        val mainContent: View = findViewById(R.id.mainContent)

        networkViewModel.isNetworkAvailable.observe(this) { isAvailable ->
            if (isAvailable) {
                errorScreen.visibility = View.GONE
                mainContent.visibility = View.VISIBLE
            } else {
                errorScreen.visibility = View.VISIBLE
                mainContent.visibility = View.GONE
            }
        }

    }
}