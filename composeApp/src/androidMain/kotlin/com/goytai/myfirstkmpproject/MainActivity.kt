package com.goytai.myfirstkmpproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.goytai.myfirstkmpproject.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        instance = this

        setContent {
            App()
        }
    }

    companion object {
        lateinit var instance: MainActivity
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}