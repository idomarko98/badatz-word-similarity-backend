package com.example.words.similarities.services

import org.springframework.beans.factory.annotation.Autowired
import java.io.BufferedReader
import java.io.FileReader

class DictionaryMinerService(@Autowired val wordsService: WordsService) {

    fun mineDictionary(path: String) {
        BufferedReader(FileReader(path)).use { reader ->
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                if (line != "") {
                    wordsService.addNewWord(line!!)
                }
            }
        }
    }

}