package com.example.words.similarities.controllers

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.WordToAdd
import com.example.words.similarities.services.WordsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MainController(@Autowired val wordsService: WordsService) {

    @GetMapping("/api/v1/similar")
    fun getSimilarity(@RequestParam word: String): SimilarResult {
        return wordsService.getSimilarWords(word)
    }

    @PostMapping("/api/v1/add-word")
    fun addNewWord(@RequestBody wordToAdd: WordToAdd) {
        return wordsService.addNewWord(wordToAdd.word)
    }

}