package com.anilderin.fallingwords.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anilderin.fallingwords.R
import com.anilderin.fallingwords.databinding.FragmentGameBinding
import com.anilderin.fallingwords.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*

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

        viewModel.getWords()

        binding.apply {
            correctButton.setOnClickListener { onSuccess() }
            wrongButton.setOnClickListener { onWrong() }
            tvSpanishWord.setOnClickListener { fallDownAnimation() }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, "Click on the Spanish word to start!", Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess() {
        viewModel.onCorrect()
        updateScoreText()
        viewModel.getWords()
        fetchWords()
        fallDownAnimation()
        gameWin()
    }

    private fun onWrong() {
        viewModel.onWrong()
        updateLiveText()
        viewModel.getWords()
        fetchWords()
        fallDownAnimation()
        gameOver()

    }

    private fun fetchWords() {
        binding.tvWord.text = viewModel.wordPairLiveData.value?.textEng
        binding.tvSpanishWord.text = viewModel.wordPairLiveData.value?.textSpa
    }

    private fun updateLiveText() {
        binding.tvLives.text = viewModel.lives.toString()
    }

    private fun fallDownAnimation() {
        val animationSlideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        tvSpanishWord.startAnimation(animationSlideDown)
    }

    private fun gameOver() {
        if (viewModel.lives == 0) {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment())
        }
    }

    private fun gameWin() {
        if (viewModel.score == 20) {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToWinFragment())
        }
    }

    private fun updateScoreText() {
        binding.tvScore.text = viewModel.score.toString()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}