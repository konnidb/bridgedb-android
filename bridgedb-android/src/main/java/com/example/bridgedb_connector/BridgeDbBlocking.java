package com.example.bridgedb_connector;

import bridgedb.QueryServiceGrpc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BridgeDbBlocking {
    private BridgeDbConnection connection;
    private QueryServiceGrpc.QueryServiceBlockingStub stub;

    BridgeDbBlocking(BridgeDbConnection connection) {
        this.connection = connection;
    }

    private void createStub() {

        this.stub = QueryServiceGrpc.newBlockingStub(connection);
    }

}
