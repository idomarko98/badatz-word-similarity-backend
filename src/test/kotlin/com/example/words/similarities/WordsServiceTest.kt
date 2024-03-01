package com.example.words.similarities

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.Word
import com.example.words.similarities.repos.WordsRepository
import com.example.words.similarities.services.WordsService
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

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

        assert(actualListResult == expectedSimilarResult)
    }

    @Test
    fun `Given a word to add, When adding, save word to dictionary`(){
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


}