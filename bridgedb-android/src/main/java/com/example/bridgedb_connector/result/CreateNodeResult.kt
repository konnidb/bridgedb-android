package com.example.bridgedb_connector.result

import bridgedb.CreateNodeReq
import bridgedb.CreateNodeResponse
import com.example.bridgedb_connector.BridgeDbBlocking

class CreateNodeResult (
    var bridgeDbBlocking: BridgeDbBlocking,
    var createNodeReq: CreateNodeReq
){
    lateinit var createNodeResponse: CreateNodeResponse
    
    fun execute() {
        createNodeResponse = bridgeDbBlocking.stub.createNode(createNodeReq)
    }

}