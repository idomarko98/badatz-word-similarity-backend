package com.example.words.similarities.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader

@Service
class DictionaryMinerService(@Autowired val wordsService: WordsService) {

    val logger: Logger = LoggerFactory.getLogger(DictionaryMinerService::class.java)

    fun mineDictionary(path: String) {
        logger.info("Started mining words...")

        BufferedReader(FileReader(path)).use { reader ->
            var line: String?
            val wordsList = mutableListOf<String>()
            while (reader.readLine().also { line = it } != null) {
                wordsList.add(line!!)
            }

            wordsService.addNewWords(wordsList)

        }

        logger.info("Finished mining words")
    }

    @EventListener(ApplicationReadyEvent::class)
    fun startMining() {
        val path = "src/main/resources/words_dataset.txt"

        if (shouldMineWords()) {
            mineDictionary(path)
        }
    }

    private fun shouldMineWords(): Boolean {
        return wordsService.getNumberOfWords() == 0L
    }

}