package com.anilderin.fallingwords.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anilderin.fallingwords.R
import com.anilderin.fallingwords.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}