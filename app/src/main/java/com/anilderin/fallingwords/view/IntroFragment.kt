package com.anilderin.fallingwords.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anilderin.fallingwords.R
import com.anilderin.fallingwords.databinding.FragmentIntroBinding

class IntroFragment : Fragment(R.layout.fragment_intro) {

    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            startButton.setOnClickListener {
                findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToGameFragment())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}