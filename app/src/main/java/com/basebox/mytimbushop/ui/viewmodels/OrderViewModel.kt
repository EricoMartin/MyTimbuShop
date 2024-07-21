package com.basebox.mytimbushop.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.basebox.mytimbushop.models.Orders
import com.basebox.mytimbushop.repo.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class OrderViewModel(
    private val repository: OrderRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val allOrders: LiveData<List<Orders>> = repository.allOrders.asLiveData()

    fun insert(orders: Orders) {
        viewModelScope.launch(dispatcher) {
            repository.insert(orders)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(dispatcher) {
            repository.deleteAll()
        }
    }
}