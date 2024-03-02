package com.example.words.similarities.repos

import com.example.words.similarities.entities.ApiRequest
import com.example.words.similarities.entities.ApiRequestId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface ApiRequestsRepository : CrudRepository<ApiRequest, ApiRequestId> {
    @Query(value = "SELECT AVG(process_time) FROM api_requests", nativeQuery = true)
    fun getAverageProcessingTime(): Double

    @Query(
        value = """
            SELECT AVG(process_time)
            FROM api_requests 
            WHERE (cast(:from_date as date) is null or timestamp >= :from_date) 
            AND (cast(:to_date as date) is null or timestamp <= :to_date)
            """, nativeQuery = true
    )
    fun getAverageProcessingTimeWithTimeFrame(
        @Param("from_date") fromDate: Instant?,
        @Param("to_date") toDate: Instant?
    ): Double?

    @Query(value = "SELECT count(*) FROM api_requests WHERE :where_query", nativeQuery = true)
    fun countWithTimeFrame(@Param("where_query") whereQuery: String): Int

    @Query(
        value = """
            SELECT count(*)
            FROM api_requests 
            WHERE (cast(:from_date as date) is null or timestamp >= :from_date) 
            AND (cast(:to_date as date) is null or timestamp <= :to_date)
            """,
        nativeQuery = true
    )
    fun countWithTimeFrame(@Param("from_date") fromDate: Instant?, @Param("to_date") toDate: Instant?): Int
}