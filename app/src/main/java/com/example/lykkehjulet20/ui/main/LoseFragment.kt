package com.example.lykkehjulet20.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lykkehjulet20.R
import com.example.lykkehjulet20.databinding.LoseFragmentBinding
import com.example.lykkehjulet20.databinding.StartFragmentBinding
import com.example.lykkehjulet20.model.MainViewModel

class LoseFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: LoseFragmentBinding? = null
    private val binding get() = _binding!!


    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.lose_fragment, container, false)
    }

    fun restartGame(){
        sharedViewModel.restartGame()

        findNavController().navigate(R.id.action_winFragment_to_startFragment)
    }


}