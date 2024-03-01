package com.example.words.similarities.repos

import com.example.words.similarities.entities.ApiRequest
import com.example.words.similarities.entities.ApiRequestId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ApiRequestsRepository : CrudRepository<ApiRequest, ApiRequestId> {
    @Query(value = "SELECT AVG(process_time) FROM api_requests", nativeQuery = true)
    fun getAverageProcessingTime(): Double
}