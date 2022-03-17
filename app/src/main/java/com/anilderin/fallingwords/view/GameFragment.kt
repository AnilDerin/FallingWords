package com.anilderin.fallingwords.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.anilderin.fallingwords.R
import com.anilderin.fallingwords.databinding.FragmentGameBinding
import com.anilderin.fallingwords.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameFragment : Fragment(R.layout.fragment_game) {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        binding.correctButton.setOnClickListener { onSuccess() }
        binding.wrongButton.setOnClickListener { onWrong() }
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWords()

        viewModel.wordPairLiveData.observe(viewLifecycleOwner, Observer {
            it.textEng.let { binding.tvWord.toString() }
        })
    }



    private fun onSuccess() {
        viewModel.onCorrect()
        updateScoreText()
    }

    private fun onWrong() {
        viewModel.onWrong()
        updateLiveText()
    }

    private fun updateLiveText() {
        binding.tvLives.text = viewModel.lives.toString()
    }

    private fun updateScoreText() {
        binding.tvScore.text = viewModel.score.toString()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}