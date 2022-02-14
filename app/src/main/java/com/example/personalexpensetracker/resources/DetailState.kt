package com.example.personalexpensetracker.resources

import com.example.personalexpensetracker.model.Transaction

sealed class DetailState{
    object Loading: DetailState()
    object Empty: DetailState()
    data class Success(val transaction: Transaction): DetailState()
    data class Error(val exception:Throwable): DetailState()
}
