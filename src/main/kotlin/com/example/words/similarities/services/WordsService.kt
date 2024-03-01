package com.example.words.similarities.services

import com.example.words.similarities.entities.SimilarResult
import com.example.words.similarities.entities.Word
import com.example.words.similarities.exception.BadRequestException
import com.example.words.similarities.exception.DuplicateKeyException
import com.example.words.similarities.repos.WordsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WordsService(@Autowired val wordsRepository: WordsRepository) {

    fun getSimilarWords(word: String): SimilarResult {
        val keyWord = sortString(word)

        val similarWords = wordsRepository.findAllByKeyWord(keyWord)
        val similarResultList = mutableListOf<String>()
        for(similarWord in similarWords){
            similarResultList.addLast(similarWord.word)
        }

        return SimilarResult(similarResultList)
    }

    fun addNewWord(word: String) {
        if(word == "")
        {
            throw BadRequestException("Bad argument - empty word")
        }

        if(wordExistsInDict(word)){
            throw DuplicateKeyException("$word is already in dictionary")
        }

        val wordKey = sortString(word)
        val wordToSave = Word(wordKey, word)

        wordsRepository.save(wordToSave)
    }

    private fun sortString(string: String): String{
        val stringArray = string.toCharArray()
        stringArray.sort()
        return stringArray.joinToString("")
    }

    private fun wordExistsInDict(word: String): Boolean {
        return wordsRepository.findAllByWord(word).isNotEmpty()
    }
}