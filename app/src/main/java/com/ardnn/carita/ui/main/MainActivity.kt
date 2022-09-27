package com.ardnn.carita.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setupInject()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setupInject() {
        (application as CaritaApplication).applicationComponent.inject(this)
    }
}