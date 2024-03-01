package com.example.words.similarities.services

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.Word
import com.example.words.similarities.repos.WordsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class WordsService(@Autowired val wordsRepository: WordsRepository) {

    fun getSimilarWords(word: String): SimilarResult {
        val wordArray = word.toCharArray()
        wordArray.sort()
        val keyWord = wordArray.joinToString("")

        val similarWords = wordsRepository.findAllByKeyWord(keyWord)
        val similarResultList = mutableListOf<String>()
        for(similarWord in similarWords){
            similarResultList.addLast(similarWord.word)
        }

        return SimilarResult(similarResultList)
    }
}