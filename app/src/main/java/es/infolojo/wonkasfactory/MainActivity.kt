package es.infolojo.wonkasfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import es.infolojo.wonkasfactory.databinding.ActivityMainBinding
import es.infolojo.wonkasfactory.ui.viewmodel.WonkasListViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val wonkasListViewModel by lazy {
        ViewModelProvider(this).get(WonkasListViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        //Change theme to quit splash
        this.setTheme(R.style.Theme_WonkasFactory)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)

        wonkasListViewModel.init()

        navController = Navigation.findNavController(this, R.id.navigation_host)
    }

    private fun initNavigation(){
//        navController.addOnDestinationChangedListener{_, destination, _ ->
//
//            when (destination) {
//                //TODO DESTINTATIONS ESPECIFIC CONFIG
//            }
//        }
    }
}