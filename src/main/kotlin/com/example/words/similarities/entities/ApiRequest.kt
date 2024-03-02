package com.example.words.similarities.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "api_requests")
@IdClass(ApiRequestId::class)
class ApiRequest(@Id val timestamp: Instant = Instant.now(),@Id val processTime: Double = 0.0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ApiRequest

        if (timestamp != other.timestamp) return false
        if (processTime != other.processTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + processTime.hashCode()
        return result
    }
}