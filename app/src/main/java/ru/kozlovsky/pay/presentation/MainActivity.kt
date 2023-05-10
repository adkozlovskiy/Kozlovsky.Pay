package ru.kozlovsky.pay.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment.Companion.KEY_RE_INIT
import ru.kozlovsky.pay.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.am_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_transactions -> {
                    binding.amBottomNavView.isVisible = true
                }

                else -> {
                    binding.amBottomNavView.isVisible = false
                }
            }
        }
        binding.amBottomNavView.setupWithNavController(navController)
        proceedReInitToNavigation(navController)
    }

    @Suppress("deprecation")
    private fun proceedReInitToNavigation(navController: NavController) {
        binding.amBottomNavView.setOnNavigationItemSelectedListener { item ->
            // блокируем навигацию на уже выбранный элемент
            if (item.itemId != navController.currentDestination?.id) {
                navController.navigate(
                    item.itemId,
                    bundleOf(KEY_RE_INIT to true)
                )
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}