package com.example.words.similarities.entities

import java.io.Serializable
import java.time.Instant


class ApiRequestId(private val timestamp: Instant = Instant.now(), private val processTime: Double) : Serializable