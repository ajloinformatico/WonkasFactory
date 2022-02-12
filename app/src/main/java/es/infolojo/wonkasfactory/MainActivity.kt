package es.infolojo.wonkasfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import es.infolojo.wonkasfactory.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        //Change theme to quit splash
        this.setTheme(R.style.Theme_WonkasFactory)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.navigation_host)

    }

    private fun initNavigation(){
        navController.addOnDestinationChangedListener{_, destination, _ ->

            when (destination) {
                //TODO DESTINTATIONS ESPECIFIC CONFIG
            }
        }
    }
}