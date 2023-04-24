package ru.kozlovsky.pay.presentation.home

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentHomeBinding
import ru.kozlovsky.pay.domain.navigation.navigate
import ru.kozlovsky.pay.util.ShakeListener

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    private val shakeListener = ShakeListener(
        onShake = {
            viewModel.onShake()
        }
    )

    override fun configureView() {
        super.configureView()
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        with(binding) {
            ftMcvMakeTransactions.setOnClickListener {
//                viewModel.navigate(R.id.transactionFragment)
            }
            ftToolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_settings) {
//                    viewModel.navigate(R.id.settingsFragment)
                    true
                } else false
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding) {

        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(shakeListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        sensorManager?.unregisterListener(shakeListener)
        super.onPause()
    }
}