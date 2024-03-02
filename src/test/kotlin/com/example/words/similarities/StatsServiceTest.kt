package com.example.words.similarities

import com.example.words.similarities.entities.StatsResult
import com.example.words.similarities.repos.ApiRequestsRepository
import com.example.words.similarities.repos.WordsRepository
import com.example.words.similarities.services.StatsService
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
class StatsServiceTest {

    private final val mockedWordsRepository = mockk<WordsRepository>()
    private final val mockedApiRequestRepository = mockk<ApiRequestsRepository>()

    val statsService: StatsService = StatsService(mockedWordsRepository, mockedApiRequestRepository)

    @Test
    fun `Given no timeframe, When getting stats, Then return stats`() {
        val fromDate = null
        val toDate = null
        val expectedTotalWords = 1L
        val expectedTotalReqs = 2L
        val expectedAvgTime = 182.0
        every { mockedWordsRepository.count() } returns expectedTotalWords
        every { mockedApiRequestRepository.count() } returns expectedTotalReqs
        every { mockedApiRequestRepository.getAverageProcessingTime() } returns expectedAvgTime
        val expectedStatResult =
            StatsResult(expectedTotalWords.toInt(), expectedTotalReqs.toInt(), expectedAvgTime.toInt())

        val actualStatResult = statsService.getStats(fromDate, toDate)

        assert(expectedStatResult == actualStatResult)
    }

    @Test
    fun `Given timeframe, When getting stats, Then return stats`() {
        val fromDate = Instant.parse("2022-01-01T00:00:00Z")
        val toDate = Instant.parse("2024-01-01T00:00:00Z")
        val expectedTotalWords = 1
        val expectedTotalReqs = 2
        val expectedAvgTime = 182.0
        every { mockedWordsRepository.countWithTimeFrame(fromDate,toDate) } returns expectedTotalWords
        every { mockedApiRequestRepository.countWithTimeFrame(fromDate, toDate) } returns expectedTotalReqs
        every { mockedApiRequestRepository.getAverageProcessingTimeWithTimeFrame(fromDate,toDate) } returns expectedAvgTime
        val expectedStatResult =
            StatsResult(expectedTotalWords, expectedTotalReqs, expectedAvgTime.toInt())

        val actualStatResult = statsService.getStats(fromDate, toDate)

        assert(expectedStatResult == actualStatResult)
    }

}