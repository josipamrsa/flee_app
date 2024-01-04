package com.example.fleeapp.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fleeapp.common.trySuspended
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val mutableUIState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Idle)
    val stateFlow: StateFlow<UIState> get() = mutableUIState

    /*

    RE: Generics VS Any
    https://www.reddit.com/r/Kotlin/comments/8ts6vj/comment/e19w9ly/?utm_source=share&utm_medium=web2x&context=3

    // TODO research sharedflow events

    protected val mutableUIEvent : MutableSharedFlow<Any> = MutableSharedFlow()
    val eventFlow: SharedFlow<Any> get() = mutableUIEvent

    protected suspend fun emitEvent(event: Any) {
        eventFlow.emit(event)
    }

    */

    protected suspend fun <T> trySuspendedWithErrorHandling(
        action: suspend () -> T
    ): Result<T> {
        return trySuspended(action).also { result ->
            // Returns exception if failure, handled by let {}, or
            // returns null if success so let {} block never executes
            result.exceptionOrNull()?.let { mutableUIState.emit(UIState.Error(it)) }
        }
    }

    protected suspend fun launchWithProgress(
        loadingState: UIState = UIState.Loading,
        successState: UIState? = UIState.Idle,
        action: suspend () -> Unit
    ) {
        mutableUIState.emit(loadingState)
        return trySuspendedWithErrorHandling(action).let {
            if (successState != null)
            // if successState is not null, check if result is failure
            // or success, and then exec let {} block accordingly
                it.getOrNull()?.let { mutableUIState.emit(successState) }
        }
    }

    protected fun launchIn(
        coroutineScope: CoroutineScope = viewModelScope,
        action: suspend () -> Unit,
    ) {
        // TIL {} syntax - no return value
        // assign = syntax - return value
        coroutineScope.launch { action() }
    }

    protected fun <T> launchOn(
        flow: Flow<T>,
        coroutineScope: CoroutineScope = viewModelScope,
    ) {
        flow.launchIn(coroutineScope)
    }

    protected fun launchWithProgressIn(
        coroutineScope: CoroutineScope = viewModelScope,
        loadingState: UIState = UIState.Loading,
        successState: UIState? = UIState.Idle,
        action: suspend () -> Unit
    ) = coroutineScope.launch {
        launchWithProgress(
            action = action,
            loadingState = loadingState,
            successState = successState
        )
    }.also {
        it.then {
            delay(200)
            if (stateFlow.value !is UIState.Error)
                mutableUIState.emit(UIState.Idle)
        }
    }

    protected fun Job.then(
        coroutineScope: CoroutineScope = viewModelScope,
        action: suspend () -> Unit
    ) = this.invokeOnCompletion {
        coroutineScope.launch { action() }
    }

}


interface UIState {
    object Idle : UIState
    object Loading : UIState
    data class Error(val throwable: Throwable) : UIState
    data class Success<out R>(val res: R) : UIState
}

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}