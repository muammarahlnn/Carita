package com.ardnn.carita.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardnn.carita.R
import com.ardnn.carita.domain.base.NoParams
import com.ardnn.carita.domain.onboarding.interactor.SaveHasBeenLaunched
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file OnBoardingViewModel, 22/01/2023 12.49 by Muammar Ahlan Abimanyu
 */
class OnBoardingViewModel @Inject constructor(
    private val saveHasBeenLaunched: SaveHasBeenLaunched
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnBoardingUiState>(OnBoardingUiState.None)
    val uiState = _uiState.asStateFlow()

    fun saveHasBeenLaunched() {
        // doesn't in on onSuccess, onError or onCompletion
        saveHasBeenLaunched.execute(
            params = NoParams,
            onSuccess = {
                _uiState.update {
                    OnBoardingUiState.OnSuccessSaveHasBeenLaunched
                }
            },
            onError = {
                _uiState.update {
                    OnBoardingUiState.ErrorToast(R.string.error_system_busy)
                }
            },
            coroutineScope = viewModelScope
        )
    }
}