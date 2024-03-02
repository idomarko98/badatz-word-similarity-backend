package com.example.words.similarities

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.Word
import com.example.words.similarities.exception.BadRequestException
import com.example.words.similarities.exception.DuplicateKeyException
import com.example.words.similarities.repos.WordsRepository
import com.example.words.similarities.services.StatsService
import com.example.words.similarities.services.WordsService
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant


@SpringBootTest(classes = [WordsServiceTest::class])
class WordsServiceTest {

    private final val mockedWordsRepository = mockk<WordsRepository>()
    private final val mockedStatsService = mockk<StatsService>()

    val wordService = WordsService(mockedWordsRepository, mockedStatsService)

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given a word, When trying to find similar words, return similar words`() {
        val word = "apple"
        val expectedKeyWord = "aelpp"
        val expectedList = listOf("apple", "pepla")
        val expectedSimilarResult = SimilarResult(expectedList)

        val firstExpectedWord = Word(expectedKeyWord, "apple")
        val secondExpectedWord = Word(expectedKeyWord, "pepla")
        val expectedWordList = listOf(firstExpectedWord, secondExpectedWord)

        every { mockedWordsRepository.findAllByKeyWord(expectedKeyWord) } returns expectedWordList
        justRun { mockedStatsService.saveRequestStat(any()) }

        val actualListResult = wordService.getSimilarWords(word)

        verify { mockedStatsService.saveRequestStat(any()) }
        assert(actualListResult == expectedSimilarResult)
    }

    @Test
    fun `Given a word to add, When adding, save word to dictionary`() {
        val fixedInstant = Instant.parse("2022-01-01T00:00:00Z")
        mockkStatic(Instant::class).run {
            every { Instant.now() } returns fixedInstant
        }
        val wordString = "apple"
        val wordKey = "aelpp"
        val wordToSave = Word(wordKey, wordString)

        every { mockedWordsRepository.findAllByWord(wordString) } returns listOf()
        every { mockedWordsRepository.save(wordToSave) } returns mockk<Word>()

        wordService.addNewWord(wordString)

        verify { mockedWordsRepository.save(wordToSave) }
    }

    @Test
    fun `Given an empty word to add, When adding, throw error`() {
        val wordString = ""

        assertThrows(BadRequestException::class.java) {
            wordService.addNewWord(wordString)
        }

    }

    @Test
    fun `Given an existing word to add, When adding, throw duplicate error`() {
        val wordString = "apple"
        val wordKey = "aelpp"
        val existingWord = Word(wordKey, wordString)

        every { mockedWordsRepository.findAllByWord(wordString) } returns listOf(existingWord)

        assertThrows(DuplicateKeyException::class.java) {
            wordService.addNewWord(wordString)
        }

    }
}