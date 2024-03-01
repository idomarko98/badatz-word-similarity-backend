package com.example.words.similarities.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "words")
@IdClass(WordId::class)
class Word(@Id val wordKey: String = "", @Id val word: String = "", val timestamp: Instant = Instant.now()) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Word

        if (wordKey != other.wordKey) return false
        if (word != other.word) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = wordKey.hashCode()
        result = 31 * result + word.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }

}