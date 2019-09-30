package com.example.bridgedb_connector;

import com.example.bridgedb_connector.result.Result;
import java.util.Map;
import bridgedb.CreateNodeReq;
import bridgedb.QueryServiceGrpc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BridgeDbBlocking {
    private BridgeDbConnection connection;
    private QueryServiceGrpc.QueryServiceBlockingStub stub;
    private Result result;

    BridgeDbBlocking(BridgeDbConnection connection) {
        this.connection = connection;
    }

    public BridgeDbBlocking init() {
        createStub();
        return this;
    }

    public QueryServiceGrpc.QueryServiceBlockingStub createStub() {
        this.stub = QueryServiceGrpc.newBlockingStub(connection.getChannel());
        return this.stub;
    }

    public <T> void createNode(T node) {
        CreateNodeReq.Builder builder = CreateNodeReq.newBuilder()
                .setToken(connection.getToken());
        Map<String, String> fieldsMap = builder.getFieldsMap();
        fieldsMap.put("property", "token");
        CreateNodeReq req = builder.build();
        stub.createNode(req);
    }

}
