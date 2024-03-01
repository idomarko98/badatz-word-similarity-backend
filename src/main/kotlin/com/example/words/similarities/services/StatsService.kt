package com.example.words.similarities.services

import com.example.words.similarities.entities.ApiRequest
import com.example.words.similarities.entities.StatsResult
import com.example.words.similarities.repos.ApiRequestsRepository
import com.example.words.similarities.repos.WordsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class StatsService(@Autowired val wordsRepository: WordsRepository, @Autowired val apiRequestsRepository: ApiRequestsRepository) {

    fun getStats(): StatsResult {
        val numberOfWords = wordsRepository.count()
        val numberOfRequest = apiRequestsRepository.count()
        val avgProcessingTime = apiRequestsRepository.getAverageProcessingTime()

        return StatsResult(numberOfWords.toInt(), numberOfRequest.toInt(), avgProcessingTime.toInt())
    }

    fun saveRequestStat(processTime: Double) {
        apiRequestsRepository.save(ApiRequest(Instant.now(), processTime))
    }

}