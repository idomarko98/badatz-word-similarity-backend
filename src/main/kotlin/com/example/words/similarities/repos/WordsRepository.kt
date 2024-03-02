package com.example.words.similarities.repos

import com.example.words.similarities.entities.Word
import com.example.words.similarities.entities.WordId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface WordsRepository : CrudRepository<Word, WordId> {

    @Query(value = "SELECT * FROM words WHERE word_key = :word_key", nativeQuery = true)
    fun findAllByKeyWord(@Param("word_key") wordKey: String): List<Word>

    @Query(value = "SELECT * FROM words WHERE word = :word", nativeQuery = true)
    fun findAllByWord(@Param("word") word: String): List<Word>

    @Query(
        value = """
            SELECT count(*)
            FROM words 
            WHERE (cast(:from_date as date) is null or timestamp >= :from_date) 
            AND (cast(:to_date as date) is null or timestamp <= :to_date)
            """,
        nativeQuery = true
    )
    fun countWithTimeFrame(@Param("from_date") fromDate: Instant?, @Param("to_date") toDate: Instant?): Int


}