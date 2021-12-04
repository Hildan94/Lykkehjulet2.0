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
import com.example.lykkehjulet20.databinding.StartFragmentBinding
import com.example.lykkehjulet20.databinding.WinFragmentBinding
import com.example.lykkehjulet20.model.MainViewModel
import kotlin.system.exitProcess
import androidx.core.app.ActivityCompat.finishAffinity as finishAffinity1

class WinFragment : Fragment() {


    private var _binding: WinFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WinFragmentBinding.inflate(inflater, container, false)
       val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel  = sharedViewModel
            winFragment = this@WinFragment

            replayButton.setOnClickListener{replayGame()}
        }
    }



    fun replayGame(){
    sharedViewModel.restartGame()
    findNavController().navigate(R.id.action_winFragment_to_startFragment)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}