package com.example.lykkehjulet20.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lykkehjulet20.databinding.GameFragmentBinding
import com.example.lykkehjulet20.model.MainViewModel
import androidx.navigation.fragment.findNavController
import com.example.lykkehjulet20.R

class GameFragment : Fragment() {

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            gameFragment = this@GameFragment

            guessButton.setOnClickListener{guess()}
            replayButton.setOnClickListener{replayGame()}
        }
    }


    private fun guess() {
        val playerWord = binding.textInputEditText.text.toString()[0]
        sharedViewModel.guessLetter(playerWord)
        if(sharedViewModel.isWordGuessed()) {
            win()
        }
        else
            sharedViewModel.decreaseLife()
        if(sharedViewModel.lives.value == 0) {
        lose()
        }
    }

    fun win() {
        binding.guessButton.setOnClickListener {
            findNavController().navigate(R.id.action_GameFragment_to_winFragment)
        }
    }

    fun lose() {
        binding.guessButton.setOnClickListener {
            findNavController().navigate(R.id.action_GameFragment_to_loseFragment)
        }
    }

    fun replayGame() {
        sharedViewModel.restartGame()
        findNavController().navigate(R.id.action_GameFragment_to_startFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}