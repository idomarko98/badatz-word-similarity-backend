package com.example.words.similarities.controllers

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.StatsResult
import com.example.words.similarities.entities.WordToAdd
import com.example.words.similarities.services.StatsService
import com.example.words.similarities.services.WordsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MainController(@Autowired val wordsService: WordsService, @Autowired val statsService: StatsService) {

    @GetMapping("/api/v1/similar")
    fun getSimilarity(@RequestParam word: String): SimilarResult {
        return wordsService.getSimilarWords(word)
    }

    @GetMapping("/api/v1/stats")
    fun getStats(): StatsResult {
        return statsService.getStats()
    }

    @PostMapping("/api/v1/add-word")
    fun addNewWord(@RequestBody wordToAdd: WordToAdd) {
        return wordsService.addNewWord(wordToAdd.word)
    }

}