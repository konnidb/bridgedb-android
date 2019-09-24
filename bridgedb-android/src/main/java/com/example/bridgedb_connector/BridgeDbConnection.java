package com.example.bridgedb_connector;

import bridgedb.QueryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BridgeDB {
    private String hostname;
    private String username;
    private String password;
    private Integer port;
    private boolean isUnsafe = true;
    private String token;
    private QueryServiceGrpc.QueryServiceStub stub;
    private ManagedChannel channel;
    private boolean isBlocking = true;

    BridgeDB(String hostname,
             String username,
             String password,
             Integer port,
             boolean isUnsafe
    ) {
        this.isUnsafe = isUnsafe;
        this.username = username;
        this.hostname = hostname;
        this.password = password;
        this.port = port;
    }
    BridgeDB(
            String hostname,
            String username,
            String password,
            Integer port
    ) {
        this.username = username;
        this.hostname = hostname;
        this.password = password;
        this.port = port;
    }

    public BridgeDB createStub() {
        return this;
    }

    public BridgeDB withAsyncStub() {
        isBlocking = false;
        this.stub = QueryServiceGrpc.newStub(channel);
        return this;
    }

    public BridgeDB withBlockingStub() {
        isBlocking = true;
        return this;
    }

    public BridgeDB connect() {
        createChannel();
        return this;
    }

    private BridgeDB createChannel() {
        if (isUnsafe) {
            channel = ManagedChannelBuilder.forAddress(hostname, port).usePlaintext().build();
        } else {
            channel = ManagedChannelBuilder.forAddress(hostname, port).build();
        }
        return this;
    }

}
