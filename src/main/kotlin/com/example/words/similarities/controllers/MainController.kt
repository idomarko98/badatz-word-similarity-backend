package com.example.words.similarities.controllers

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.StatsResult
import com.example.words.similarities.entities.WordToAdd
import com.example.words.similarities.services.StatsService
import com.example.words.similarities.services.WordsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class MainController(@Autowired val wordsService: WordsService, @Autowired val statsService: StatsService) {

    @GetMapping("/api/v1/similar")
    fun getSimilarity(@RequestParam word: String): SimilarResult {
        return wordsService.getSimilarWords(word)
    }

    @GetMapping("/api/v1/stats")
    fun getStats(@RequestParam from: Instant?, @RequestParam to: Instant?): StatsResult {
        return statsService.getStats(from, to)
    }

    @PostMapping("/api/v1/add-word")
    fun addNewWord(@RequestBody wordToAdd: WordToAdd) {
        return wordsService.addNewWord(wordToAdd.word)
    }

}