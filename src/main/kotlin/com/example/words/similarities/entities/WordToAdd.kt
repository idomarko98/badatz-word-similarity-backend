package com.example.words.similarities.entities

class WordToAdd (val word: String){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WordToAdd

        return word == other.word
    }

    override fun hashCode(): Int {
        return word.hashCode()
    }
}