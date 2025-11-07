package com.alanturing.proyecto_general_punto_de_control_i_textimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.alanturing.proyecto_general_punto_de_control_i_textimage.ui.AcercaDe
import com.alanturing.proyecto_general_punto_de_control_i_textimage.ui.AcercaDe
import com.alanturing.proyecto_general_punto_de_control_i_textimage.ui.theme.HostalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HostalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AcercaDe(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
