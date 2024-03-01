package com.example.words.similarities.entities

class SimilarResult (val similar: List<String>){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimilarResult

        return similar == other.similar
    }

    override fun hashCode(): Int {
        return similar.hashCode()
    }
}