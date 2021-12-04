package com.example.lykkehjulet20.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lykkehjulet20.R
import com.example.lykkehjulet20.model.MainViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lykkehjulet20.databinding.StartFragmentBinding

class StartFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: StartFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //NAvigerer til start interfacet
        binding.startbutton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_GameFragment)
        }
        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}