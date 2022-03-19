package com.anilderin.fallingwords.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anilderin.fallingwords.R
import com.anilderin.fallingwords.databinding.FragmentLoseBinding

class LoseFragment : Fragment(R.layout.fragment_lose) {

    private var _binding: FragmentLoseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentLoseBinding.inflate(inflater, container, false)

        binding.apply {
            restartButton.setOnClickListener {
                findNavController().navigate(LoseFragmentDirections.actionScoreFragmentToGameFragment())
            }
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}