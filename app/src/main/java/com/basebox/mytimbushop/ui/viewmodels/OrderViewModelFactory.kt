package com.basebox.mytimbushop.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basebox.mytimbushop.repo.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher

class OrderViewModelFactory(private val repository: OrderRepository,
                            private val dispatcher: CoroutineDispatcher
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrderViewModel(repository, dispatcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}