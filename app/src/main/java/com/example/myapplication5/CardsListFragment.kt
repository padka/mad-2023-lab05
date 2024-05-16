package com.example.myapplication5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication5.databinding.FragmentCardsListBinding

class CardsListFragment : Fragment() {

    private lateinit var binding: FragmentCardsListBinding
    private val viewModel: CardsListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardsListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = CardAdapter(viewModel.cards.value ?: emptyList(), this::onDeleteCard, this::onCardClick)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_cardsListFragment_to_editCardFragment)
        }

        viewModel.cards.observe(viewLifecycleOwner, {
            (binding.recyclerView.adapter as CardAdapter).notifyDataSetChanged()
        })

        return binding.root
    }

    private fun onDeleteCard(card: Card) {
        AlertDialog.Builder(requireContext())
            .setTitle("Удалить карточку")
            .setMessage("Вы уверены, что хотите удалить эту карточку?")
            .setPositiveButton("Да") { _, _ ->
                viewModel.deleteCard(card)
            }
            .setNegativeButton("Нет", null)
            .show()
    }

    private fun onCardClick(card: Card) {
        val action = CardsListFragmentDirections.actionCardsListFragmentToViewCardFragment(card)
        findNavController().navigate(action)
    }
}
