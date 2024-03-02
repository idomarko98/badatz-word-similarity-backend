package com.example.words.similarities.services

import org.springframework.beans.factory.annotation.Autowired
import java.io.BufferedReader
import java.io.FileReader
import java.time.Instant

class DictionaryMinerService(@Autowired val wordsService: WordsService) {

    fun mineDictionary(path: String) {
        val timestamp = Instant.now()

        BufferedReader(FileReader(path)).use { reader ->
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                if(line != ""){
                    wordsService.addNewWord(line!!)
                }
            }
        }
    }

}