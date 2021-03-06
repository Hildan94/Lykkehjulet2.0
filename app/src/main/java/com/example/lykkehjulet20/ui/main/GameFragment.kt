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

    /*
    This function is used every time the player makes a guess
     */
    private fun guess() {
        //Get's the guessed letter and saves it in playerWord
        val playerWord = binding.textInputEditText.text.toString()[0]

        //uses the function guessletter from the sharedViewModel
        println(sharedViewModel.guessLetter(playerWord))
        if (sharedViewModel.guessLetter(playerWord) == false)
        {
            println("got into error")
            setErrorTextField(true)
        } else {
            println("no error will be printed")
            setErrorTextField(false)
        }


        //If the word is guessed the player should win
        if(sharedViewModel.isWordGuessed()) {
            win()
        } else
            //check if the player has any lives left
            if(sharedViewModel.lives.value!! <= 0) {
                lose()
            }
    }

    /*
    navigates the player to the win screen
     */
    fun win() {
        findNavController().navigate(R.id.action_GameFragment_to_winFragment)
    }

    /*
    navigates the player to the lose screen
     */
    fun lose() {
        findNavController().navigate(R.id.action_GameFragment_to_loseFragment)
    }

    /*
    Navigates back to the start screen
     */
    fun replayGame() {
        sharedViewModel.restartGame()
        findNavController().navigate(R.id.action_GameFragment_to_startFragment)
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.wrong_gues)
            println("error detected")
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
            println("error failed")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}