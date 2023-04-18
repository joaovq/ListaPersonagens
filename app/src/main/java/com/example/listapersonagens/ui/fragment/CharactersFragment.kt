package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.listapersonagens.R
import com.example.listapersonagens.databinding.FragmentCharactersBinding
import com.example.listapersonagens.model.domain.CharacterType.DISNEY
import com.example.listapersonagens.model.domain.CharacterType.RICKY_AND_MORTY
import com.example.listapersonagens.model.mapper.toDomain
import com.example.listapersonagens.network.service.DisneyService
import com.example.listapersonagens.network.service.RickyAndMortyService
import com.example.listapersonagens.ui.utils.adapter.CharactersAdapter
import com.example.listapersonagens.ui.utils.extension.gone
import com.example.listapersonagens.ui.utils.extension.loadImage
import com.example.listapersonagens.ui.utils.extension.visible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CharactersFragment : Fragment() {

    private lateinit var _binding: FragmentCharactersBinding
    private val binding get() = _binding

    private val charactersAdapter = CharactersAdapter()
    private val rickyAndMortyService: RickyAndMortyService by inject()
    private val disneyService: DisneyService by inject()

    /* DEPOIS
    * SRP =>
    *  Utilizamos esses conceitos para que o fragment neste caso, tenha somente responsabilidades da
    * view e o appContainer que disponiblize as dependências que ele precisa.
    *   Desta forma também reduzimos o código boilerplate e utilizamos só que realmente é necessário
    * */

    /** ANTES
     *
     * private val retrofitDisney: Retrofit = RetrofitConfig
     .getInstance(UrlFactory.BASE_URL_DISNEY)
     val disneyService: DisneyService = retrofitDisney.create(DisneyService::class.java)

     private val retrofitRickyAndMorty: Retrofit = RetrofitConfig
     .getInstance(UrlFactory.BASE_URL_RM)
     val rickyAndMortyService: RickyAndMortyService =
     retrofitRickyAndMorty.create(RickyAndMortyService::class.java)
     *
     *
     * */

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
                            ivCharactersTypeImage.loadImage(
                                requireView(),
                                DISNEY.imageUrl,
                                R.drawable.ic_launcher_background,
                            )

                            val disneyCharacters = disneyService.getCharacters()
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
                                R.drawable.ic_launcher_background,
                            )
                            val rickyAndMortyCharacters =
                                rickyAndMortyService.getCharacters()
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
