package eu.andreihaiu.artobjects.artObjectDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity
import eu.andreihaiu.domain.usecases.artObjectDetails.ArtObjectDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtObjectDetailsViewModel @Inject constructor(
    private val artObjectDetailsUseCase: ArtObjectDetailsUseCase
) : ViewModel() {

    private val _artObjectDetails: MutableStateFlow<ArtObjectDetailsEntity?> = MutableStateFlow(null)
    val artObjectDetails: StateFlow<ArtObjectDetailsEntity?> get() = _artObjectDetails.asStateFlow()

    fun fetchArtObjectDetails(objectId: String) {
        viewModelScope.launch {
            artObjectDetailsUseCase.invoke(objectId = objectId)
                .distinctUntilChanged()
                .collect {
                    _artObjectDetails.value = it
                }
        }
    }
}