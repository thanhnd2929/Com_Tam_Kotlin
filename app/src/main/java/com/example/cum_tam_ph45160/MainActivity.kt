package com.example.cum_tam_ph45160

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cum_tam_ph45160.BottomNavigation.MainScreen
import com.example.cum_tam_ph45160.ui.theme.Cum_Tam_Ph45160Theme

class MainActivity : ComponentActivity() {


    companion object {
        private const val REQUEST_CODE_READ_EXTERNAL_STORAGE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Cum_Tam_Ph45160Theme {
                MainScreen()
            }
        }
    }
}


