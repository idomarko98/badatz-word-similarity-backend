package com.example.words.similarities

import com.example.words.similarities.services.DictionaryMinerService
import com.example.words.similarities.services.WordsService
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DictionaryMinerTest {

    private val wordsService = mockk<WordsService>()

    val dictionaryMinerService = DictionaryMinerService(wordsService)

    @Test
    fun `Given path to dictionary, When mining, Then save words to DB`() {
        val path = "src/test/kotlin/resources/words_dataset.txt"
        justRun { wordsService.addNewWord("Blink") }
        justRun { wordsService.addNewWord("Dataset") }
        justRun { wordsService.addNewWord("Apple") }

        dictionaryMinerService.mineDictionary(path)

        verify { wordsService.addNewWord("Blink") }
        verify { wordsService.addNewWord("Dataset") }
        verify { wordsService.addNewWord("Apple") }
    }
    
}