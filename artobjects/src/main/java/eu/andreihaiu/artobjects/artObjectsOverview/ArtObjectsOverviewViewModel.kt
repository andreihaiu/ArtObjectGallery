package eu.andreihaiu.artobjects.artObjectsOverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.andreihaiu.domain.entities.ArtObjectEntity
import eu.andreihaiu.domain.usecases.artObjectsOverview.ArtObjectsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtObjectsOverviewViewModel @Inject constructor(
    private val artObjectsUseCase: ArtObjectsUseCase,
) : ViewModel() {

    private val _artObjectsList: MutableStateFlow<PagingData<ArtObjectEntity>> = MutableStateFlow(PagingData.empty())
    val artObjectsList: StateFlow<PagingData<ArtObjectEntity>> get() = _artObjectsList.asStateFlow()


    init {
        fetchArtObjectsList()
    }

    private fun fetchArtObjectsList() {
        viewModelScope.launch(Dispatchers.IO) {
            artObjectsUseCase.invoke()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _artObjectsList.value = it
                }
        }
    }
}