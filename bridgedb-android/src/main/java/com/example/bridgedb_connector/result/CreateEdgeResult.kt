package com.example.bridgedb_connector.result

import bridgedb.CreateEdgeReq
import bridgedb.CreateEdgeResponse
import com.example.bridgedb_connector.BridgeDbBlocking

class CreateEdgeResult (
    var bridgeDbBlocking: BridgeDbBlocking,
    var createEdgeReq: CreateEdgeReq
) {
    lateinit var createEdgeResponse: CreateEdgeResponse

    fun execute() {
        createEdgeResponse = bridgeDbBlocking.stub.createEdge(createEdgeReq)
    }
}