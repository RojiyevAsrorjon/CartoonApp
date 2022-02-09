 package uz.gita.cartoonapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.cartoonapp.R

 @AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host = supportFragmentManager.findFragmentById(R.id.fragmentController) as NavHostFragment
        val graph = host.navController.navInflater.inflate(R.navigation.navigation)
        host.navController.graph = graph
    }
}