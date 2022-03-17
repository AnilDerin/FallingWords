package com.anilderin.fallingwords.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.anilderin.fallingwords.R
import com.anilderin.fallingwords.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    //private lateinit var  viewModel : GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}