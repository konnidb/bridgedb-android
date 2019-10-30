package com.example.bridgedb_connector.dto

data class AuthCredentials(
    val username: String,
    val password: String,
    val database: String,
    val graph: String
)