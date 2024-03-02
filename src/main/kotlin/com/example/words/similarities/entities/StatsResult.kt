package com.example.words.similarities.entities

class StatsResult(val totalWords: Int, val totalRequest: Int, val avgProcessingTime: Int?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StatsResult

        if (totalWords != other.totalWords) return false
        if (totalRequest != other.totalRequest) return false
        if (avgProcessingTime != other.avgProcessingTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalWords
        result = 31 * result + totalRequest
        result = 31 * result + (avgProcessingTime ?: 0)
        return result
    }
}