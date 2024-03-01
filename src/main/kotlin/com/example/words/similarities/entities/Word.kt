package com.example.words.similarities.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "words")
@IdClass(WordId::class)
class Word(wordKey: String = "", word: String = "", timestamp: Instant = Instant.now()) {
    @Id
    val wordKey: String = ""

    @Id
    val word: String = ""
    val timestamp: Instant = Instant.now()
}