package com.example.myapplication5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication5.databinding.FragmentViewCardBinding

class ViewCardFragment : Fragment() {

    private lateinit var binding: FragmentViewCardBinding
    private val args: ViewCardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewCardBinding.inflate(inflater, container, false)
        binding.card = args.card

        binding.fabEdit.setOnClickListener {
            val action = ViewCardFragmentDirections.actionViewCardFragmentToEditCardFragment(args.card)
            findNavController().navigate(action)
        }

        return binding.root
    }
}
