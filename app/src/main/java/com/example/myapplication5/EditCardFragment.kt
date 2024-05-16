package com.example.myapplication5

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication5.databinding.FragmentEditCardBinding
import java.io.InputStream

class EditCardFragment : Fragment() {

    private lateinit var binding: FragmentEditCardBinding
    private val viewModel: CardsListViewModel by activityViewModels()
    private val args: EditCardFragmentArgs by navArgs()
    private var imageBitmap: Bitmap? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            imageUri?.let {
                val inputStream: InputStream? = context?.contentResolver?.openInputStream(it)
                imageBitmap = BitmapFactory.decodeStream(inputStream)
                binding.selectedImageView.setImageBitmap(imageBitmap) // Assuming you have an ImageView to show the selected image
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditCardBinding.inflate(inflater, container, false)
        binding.card = args.card
        binding.lifecycleOwner = viewLifecycleOwner

        binding.saveButton.setOnClickListener {
            saveCard()
        }

        binding.selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }

        return binding.root
    }

    private fun saveCard() {
        val updatedCard = Card(
            imageResId = args.card.imageResId,
            title = args.card.title,
            subtitle = args.card.subtitle,
            question = binding.questionEditText.text.toString(),
            hint = binding.hintEditText.text.toString(),
            answer = binding.answerEditText.text.toString(),
            translation = binding.translationEditText.text.toString(),
            description = binding.descriptionEditText.text.toString()
        )

        viewModel.updateCard(updatedCard)

        val action = EditCardFragmentDirections.actionEditCardFragmentToCardsListFragment()
        findNavController().navigate(action)
    }
}
