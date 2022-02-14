package com.example.personalexpensetracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.personalexpensetracker.R
import com.example.personalexpensetracker.databinding.ActivityMainBinding
import com.example.personalexpensetracker.repository.TransactionRepository
import com.example.personalexpensetracker.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var repository: TransactionRepository

    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel
        initViews(binding)
        observeNavElements(binding, navHostFragment.navController)
    }

    private fun observeNavElements(binding: ActivityMainBinding, navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(false)
                }
                R.id.addTransactionFragment -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(true)
                    binding.toolbar.title = getString(R.string.text_add_transaction)
                }
                else -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(true)
                }
            }
        }
    }

    private fun initViews(binding: ActivityMainBinding) {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return

        with(navHostFragment.navController) {
            appBarConfiguration = AppBarConfiguration(graph)
            setupActionBarWithNavController(this, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navHostFragment.navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}