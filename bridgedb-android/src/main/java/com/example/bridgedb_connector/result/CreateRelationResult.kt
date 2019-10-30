package com.example.bridgedb_connector.result

import bridgedb.CraeteRelationResponse
import bridgedb.CreateRelationReq
import com.example.bridgedb_connector.BridgeDbBlocking

class CreateRelationResult (
    var bridgeDbBlocking: BridgeDbBlocking,
    var createRelationReq: CreateRelationReq
) {
    lateinit var createRelationResponse: CraeteRelationResponse
    fun execute() {
        createRelationResponse = bridgeDbBlocking.stub.createRelation(createRelationReq)
    }
}