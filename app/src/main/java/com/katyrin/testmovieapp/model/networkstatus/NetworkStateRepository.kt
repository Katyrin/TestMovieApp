package com.katyrin.testmovieapp.model.networkstatus

interface NetworkStateRepository {
    suspend fun isOnline(): Boolean
}