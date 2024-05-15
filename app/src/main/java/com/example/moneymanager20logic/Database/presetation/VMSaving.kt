package com.example.moneymanager20logic.Database.presetation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymanager20logic.Database.Daos.Saving
import com.example.moneymanager20logic.Database.table.Entities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VMSaving(
    private val dao: Saving,
):ViewModel() {

    private val isSortedByDateAdded = MutableStateFlow(true)


    @OptIn(ExperimentalCoroutinesApi::class)
    private var saving = isSortedByDateAdded.flatMapLatest { sort ->
        if (sort) {
            dao.getSavingByDateAdded()
        } else {
            dao.getSavingByTitle()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    val _state = MutableStateFlow(SavingState())
    val state =
        combine(_state, isSortedByDateAdded, saving) { state, isSortedByDateAdded, saving ->
            state.copy(
                saving = saving
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SavingState())

    private val _sum= MutableStateFlow(0.0)
    val sum: StateFlow<Double> = _sum.asStateFlow()

    fun onEvent(event: MoneyEvent) {

        when (event) {
            is MoneyEvent.Delete -> {
                viewModelScope.launch {
                    dao.delete(event.saving)
                }
            }

            is MoneyEvent.Save -> {
                val save = Entities(
                    title = state.value.title.value,
                    amount = state.value.amount.value.toDouble(),
                    dateAdded = System.currentTimeMillis(),

                    )
                viewModelScope.launch {
                    dao.add(save)
                }
            }

            is MoneyEvent.Update -> {
                viewModelScope.launch {
                    updateAmount(title = event.title, amount = event.amount)
                }
            }

            is MoneyEvent.Sum -> {
               viewModelScope.launch {
                   dao.sumOftheAmount()
                       .collect{result ->
                       _sum.value=result
                   }
               }
            }
        }
    }


    private suspend fun updateAmount(amount: Double, title: String) {
        dao.updateAmount(amount, title)
    }

}


