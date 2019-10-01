package com.example.bridgedb_connector.result

import bridgedb.SearchNodeReq
import bridgedb.SearchNodeResponse
import com.example.bridgedb_connector.BridgeDbBlocking

class SearchNode (
    var bridgeDbBlocking: BridgeDbBlocking,
    var searchNodeReq: SearchNodeReq
) {
    lateinit var searchNodeResponse: SearchNodeResponse

    public fun execute() {
        searchNodeResponse = bridgeDbBlocking.stub.searchNode(searchNodeReq)
    }
}