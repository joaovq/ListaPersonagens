package com.example.listapersonagens.ui.presenter

import com.example.listapersonagens.config.presenter.BaseFragment
import com.example.listapersonagens.model.domain.CharacterType
import com.example.listapersonagens.model.mapper.toDomain
import com.example.listapersonagens.network.service.DisneyService
import com.example.listapersonagens.network.service.RickyAndMortyService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersPresenter(
    private val rickyAndMortyService: RickyAndMortyService,
    private val disneyService: DisneyService,
) : PresentationCharacters {
    override var view: CharactersView? = null

    override fun getCharacters(characterType: CharacterType) {
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.Main) {
            view?.let { safeView ->
                with(safeView) {
                    onLoading()
                    try {
                        when (characterType) {
                            CharacterType.DISNEY -> {
                                onSuccess(disneyService.getCharacters().data.toDomain())
                            }

                            CharacterType.RICKY_AND_MORTY -> {
                                onSuccess(rickyAndMortyService.getCharacters().results.toDomain())
                            }
                        }
                        onHideLoading()
                    } catch (e: Exception) {
                        onFailure(e, "Conexão com a API falhou")
                    }
                }
            }
        }
    }

    override fun attachView(view: CharactersView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }
}

interface PresentationCharacters : BaseFragment<CharactersView> {
    fun getCharacters(characterType: CharacterType)
}
