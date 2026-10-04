package com.compose.news.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.news.core.common.Result
import com.compose.news.data.repository.AuthRepositoryImpl
import com.compose.news.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val isRegister: Boolean = false,
    val displayName: String = "",
    val email: String = AuthRepositoryImpl.DEMO_EMAIL,
    val password: String = AuthRepositoryImpl.DEMO_PASSWORD,
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false,
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun onNameChange(value: String) = _uiState.update { it.copy(displayName = value, error = null) }
    fun onEmailChange(value: String) = _uiState.update { it.copy(email = value, error = null) }
    fun onPasswordChange(value: String) = _uiState.update { it.copy(password = value, error = null) }
    fun toggleMode() = _uiState.update { it.copy(isRegister = !it.isRegister, error = null) }

    fun submit() {
        val state = _uiState.value
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            val result = if (state.isRegister) {
                authUseCases.signUp(state.displayName, state.email, state.password)
            } else {
                authUseCases.signIn(state.email, state.password)
            }
            _uiState.update {
                when (result) {
                    is Result.Success -> it.copy(loading = false, success = true)
                    is Result.Error -> it.copy(loading = false, error = result.message)
                }
            }
        }
    }
}
