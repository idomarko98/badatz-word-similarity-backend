package com.example.words.similarities

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.Word
import com.example.words.similarities.repos.WordsRepository
import com.example.words.similarities.services.WordsService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WordsServiceTest() {

    private final val mockedWordsRepository = mockk<WordsRepository>()

    val wordService = WordsService(mockedWordsRepository)

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

        val actualListResult = wordService.getSimilarWords(word)

        assert(actualListResult.equals(expectedSimilarResult))

    }
}