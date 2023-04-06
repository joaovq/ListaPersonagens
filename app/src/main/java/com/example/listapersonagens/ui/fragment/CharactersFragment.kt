package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.listapersonagens.ListaDePersonagensApplication
import com.example.listapersonagens.R
import com.example.listapersonagens.databinding.FragmentCharactersBinding
import com.example.listapersonagens.model.domain.CharacterType.DISNEY
import com.example.listapersonagens.model.domain.CharacterType.RICKY_AND_MORTY
import com.example.listapersonagens.model.mapper.toDomain
import com.example.listapersonagens.ui.utils.adapter.CharactersAdapter
import com.example.listapersonagens.ui.utils.extension.gone
import com.example.listapersonagens.ui.utils.extension.loadImage
import com.example.listapersonagens.ui.utils.extension.visible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private lateinit var _binding: FragmentCharactersBinding
    private val binding get() = _binding

    private val charactersAdapter = CharactersAdapter()
    private val appContainer = (activity?.application as ListaDePersonagensApplication).appContainer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            rvCharacters.adapter = charactersAdapter
            rgCharacterType.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rbCharacterTypeDisney -> {
                        CoroutineScope(Dispatchers.IO).launch(Dispatchers.Main) {
                            pbLoadingCharacters.visible()
                            tvCharactersTypeTitle.text = DISNEY.title
                            llCharactersTypeHeader.background = AppCompatResources.getDrawable(
                                requireContext(),
                                DISNEY.colorRes,
                            )
                            Glide.with(binding.root)
                                .load(DISNEY.imageUrl)
                                .error(R.drawable.ic_launcher_background)
                                .into(ivCharactersTypeImage)

                            val disneyCharacters = appContainer.disneyService.getCharacters()
                            charactersAdapter.submitList(disneyCharacters.data.toDomain())
                            pbLoadingCharacters.gone()
                        }
                    }
                    R.id.rbCharacterTypeRickyAndMorty -> {
                        CoroutineScope(Dispatchers.IO).launch(Dispatchers.Main) {
                            pbLoadingCharacters.visible()
                            tvCharactersTypeTitle.text = RICKY_AND_MORTY.title
                            llCharactersTypeHeader.background = AppCompatResources.getDrawable(
                                requireContext(),
                                RICKY_AND_MORTY.colorRes,
                            )
                            ivCharactersTypeImage.loadImage(
                                requireView(),
                                RICKY_AND_MORTY.imageUrl,
                                R.drawable.ic_launcher_background
                            )
                            val rickyAndMortyCharacters =
                                appContainer.rickyAndMortyService.getCharacters()
                            charactersAdapter.submitList(
                                rickyAndMortyCharacters.results.toDomain(),
                            )
                            pbLoadingCharacters.gone()
                        }
                    }
                }
            }
        }
    }
}
