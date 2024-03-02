package com.example.words.similarities.services

import com.example.words.similarities.entities.ApiRequest
import com.example.words.similarities.entities.StatsResult
import com.example.words.similarities.repos.ApiRequestsRepository
import com.example.words.similarities.repos.WordsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class StatsService(
    @Autowired val wordsRepository: WordsRepository,
    @Autowired val apiRequestsRepository: ApiRequestsRepository
) {

    fun getStats(fromDate: Instant?, toDate: Instant?): StatsResult {
        if (fromDate === null && toDate === null) {
            return getStatsWithoutTimeFrame()
        }

        return getStatsWithTimeFrame(fromDate, toDate)
    }

    fun getStatsWithoutTimeFrame(): StatsResult {
        val numberOfWords = wordsRepository.count()
        val numberOfRequest = apiRequestsRepository.count()
        val avgProcessingTime = apiRequestsRepository.getAverageProcessingTime()

        return StatsResult(numberOfWords.toInt(), numberOfRequest.toInt(), avgProcessingTime.toInt())
    }

    fun getStatsWithTimeFrame(fromDate: Instant?, toDate: Instant?): StatsResult {
        val numberOfWords = wordsRepository.countWithTimeFrame(fromDate, toDate)
        val numberOfRequest = apiRequestsRepository.countWithTimeFrame(fromDate, toDate)
        val avgProcessingTime = apiRequestsRepository.getAverageProcessingTimeWithTimeFrame(fromDate, toDate)

        return StatsResult(numberOfWords, numberOfRequest, avgProcessingTime?.toInt())
    }

    fun saveRequestStat(processTime: Double) {
        apiRequestsRepository.save(ApiRequest(Instant.now(), processTime))
    }

}