package com.example.bridgedb_connector;

import bridgedb.QueryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BridgeDbConnection {
    private String hostname;
    private String username;
    private String password;
    private Integer port;
    private boolean isUnsafe = true;
    private ManagedChannel channel;
    private boolean isBlocking = true;

    BridgeDbConnection(String hostname,
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
    BridgeDbConnection(
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

    public BridgeDbConnection connect() {
        createChannel();
        return this;
    }

    private BridgeDbConnection createChannel() {
        if (isUnsafe) {
            channel = ManagedChannelBuilder.forAddress(hostname, port).usePlaintext().build();
        } else {
            channel = ManagedChannelBuilder.forAddress(hostname, port).build();
        }
        return this;
    }

}
