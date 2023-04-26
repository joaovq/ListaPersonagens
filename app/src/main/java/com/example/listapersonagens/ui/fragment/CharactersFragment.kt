package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.listapersonagens.R
import com.example.listapersonagens.databinding.FragmentCharactersBinding
import com.example.listapersonagens.model.domain.Character
import com.example.listapersonagens.model.domain.CharacterType
import com.example.listapersonagens.model.domain.CharacterType.DISNEY
import com.example.listapersonagens.model.domain.CharacterType.RICKY_AND_MORTY
import com.example.listapersonagens.network.service.DisneyService
import com.example.listapersonagens.network.service.RickyAndMortyService
import com.example.listapersonagens.ui.presenter.CharactersPresenter
import com.example.listapersonagens.ui.presenter.CharactersView
import com.example.listapersonagens.ui.utils.adapter.CharactersAdapter
import com.example.listapersonagens.ui.utils.extension.gone
import com.example.listapersonagens.ui.utils.extension.loadImage
import com.example.listapersonagens.ui.utils.extension.visible
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class CharactersFragment : Fragment(), CharactersView {

    private lateinit var _binding: FragmentCharactersBinding
    private val binding get() = _binding

    private val charactersAdapter = CharactersAdapter()
    private val presenter: CharactersPresenter by inject()

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
        presenter.attachView(this)
        onChecked()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
    }

    override fun onLoading() {
        binding.pbLoadingCharacters.visible()
    }

    override fun onSuccess(characters: List<Character>) {
        with(binding) {
            rvCharacters.adapter = charactersAdapter
            charactersAdapter.submitList(characters)
        }
    }

    private fun onChecked() {
        with(binding) {
            rgCharacterType.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rbCharacterTypeDisney -> {
                        with(DISNEY) {
                            setUpCharactersView(this)
                            presenter.getCharacters(DISNEY)
                        }
                    }

                    R.id.rbCharacterTypeRickyAndMorty -> {
                        with(RICKY_AND_MORTY) {
                            setUpCharactersView(this)
                            presenter.getCharacters(RICKY_AND_MORTY)
                        }
                    }
                }
            }
        }
    }

    private fun setUpCharactersView(
        characterType: CharacterType
    ) {
        binding.tvCharactersTypeTitle.text = characterType.title
        binding.llCharactersTypeHeader.background =
            AppCompatResources.getDrawable(
                requireContext(),
                characterType.colorRes,
            )
        binding.ivCharactersTypeImage.loadImage(
            requireView(),
            characterType.imageUrl,
            R.drawable.ic_launcher_background,
        )
    }

    override fun onFailure(exception: Exception, message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
        exception.printStackTrace()
    }

    override fun onHideLoading() {
        binding.pbLoadingCharacters.gone()
    }
}
