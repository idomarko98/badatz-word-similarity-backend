package com.example.words.similarities.repos

import com.example.words.similarities.entities.Word
import com.example.words.similarities.entities.WordId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface WordsRepository : CrudRepository<Word, WordId> {

    @Query(value = "SELECT * FROM words WHERE word_key = :word_key", nativeQuery = true)
    fun findAllByKeyWord(@Param("word_key") wordKey: String): List<Word>
}