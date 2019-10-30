package com.example.bridgedb_connector.dto

import bridgedb.NetworkEdge
import bridgedb.NetworkNode

data class GraphDTO(
    val nodes: List<NetworkNode>,
    val edges: List<NetworkEdge>
)