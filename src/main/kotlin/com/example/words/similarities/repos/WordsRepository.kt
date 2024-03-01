package com.example.words.similarities.repos

import com.example.words.similarities.entities.Word
import com.example.words.similarities.entities.WordId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordsRepository : CrudRepository<Word, WordId>{
}