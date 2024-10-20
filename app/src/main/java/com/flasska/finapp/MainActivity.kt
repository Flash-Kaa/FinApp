package com.flasska.finapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.flasska.finapp.ui.screens.navigation.NavGraph
import com.flasska.finapp.ui.theme.FinAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FinAppTheme {
                NavGraph()
            }
        }
    }
}