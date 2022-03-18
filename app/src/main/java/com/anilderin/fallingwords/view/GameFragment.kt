package com.anilderin.fallingwords.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anilderin.fallingwords.R
import com.anilderin.fallingwords.databinding.FragmentGameBinding
import com.anilderin.fallingwords.viewmodel.GameViewModel
import io.reactivex.internal.operators.flowable.FlowableDoAfterNext
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
            /*tvWord.text = viewModel.wordPairLiveData.value.textEng
            tvSpanishWord.text = viewModel.wordPairLiveData.value.textSpa*/
            correctButton.setOnClickListener { onSuccess() }
            wrongButton.setOnClickListener { onWrong() }
            csLayout.setOnClickListener { fallDownAnimation() }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.wordPairLiveData.observe(viewLifecycleOwner, Observer {
            it.textEng.let { binding.tvWord.text }
        })

    }


    private fun onSuccess() {
        viewModel.onCorrect()
        updateScoreText()
    }

    private fun onWrong() {
        viewModel.onWrong()
        updateLiveText()
        gameOverText()
    }

    private fun updateLiveText() {
        binding.tvLives.text = viewModel.lives.toString()
    }

    private fun fallDownAnimation() {
        val animationSlideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        tvSpanishWord.startAnimation(animationSlideDown)

    }

    private fun gameOverText() {
        if (viewModel.lives == 0) {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment())
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