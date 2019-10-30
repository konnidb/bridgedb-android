package com.example.bridgedb_connector;

import com.example.bridgedb_connector.dto.AuthCredentials;
import com.example.bridgedb_connector.dto.GraphDTO;
import com.example.bridgedb_connector.result.CreateEdgeResult;
import com.example.bridgedb_connector.result.Result;

import java.util.List;
import java.util.Map;

import bridgedb.CreateEdgeReq;
import bridgedb.CreateEdgeResponse;
import bridgedb.CreateNodeReq;
import bridgedb.CreateNodeResponse;
import bridgedb.CreateRelationReq;
import bridgedb.DeleteNodeReq;
import bridgedb.DeleteNodeResponse;
import bridgedb.NetworkEdge;
import bridgedb.NetworkGraphRequest;
import bridgedb.NetworkGraphResponse;
import bridgedb.NetworkNode;
import bridgedb.QueryServiceGrpc;
import bridgedb.Session;
import bridgedb.SessionRequest;

public class BridgeDbBlocking {
    private BridgeDbConnection connection;
    private QueryServiceGrpc.QueryServiceBlockingStub stub;
    private String token;

    public BridgeDbBlocking(BridgeDbConnection connection) {
        this.connection = connection;
    }

    public BridgeDbBlocking initStub() {
        createStub();
        return this;
    }

    public QueryServiceGrpc.QueryServiceBlockingStub createStub() {
        this.stub = QueryServiceGrpc.newBlockingStub(connection.getChannel());
        return this.stub;
    }

    public QueryServiceGrpc.QueryServiceBlockingStub getStub() {
        return stub;
    }

    public void createSession(AuthCredentials credentials) {
        SessionRequest request = SessionRequest.newBuilder()
                .setPassword(credentials.getPassword())
                .setUsername(credentials.getUsername())
                .setDatabase(credentials.getDatabase())
                .setGraph(credentials.getGraph())
                .build();
        Session session = stub.createSession(request);
        this.token = session.getToken();
    }

    public NetworkNode createNode(NetworkNode node) {
        CreateNodeReq req = CreateNodeReq.newBuilder()
                .setToken(token)
                .setNode(node)
                .build();
        CreateNodeResponse response = stub.createNode(req);
        return response.getNode();
    }

    public NetworkNode deleteNode(long nodeId){
        DeleteNodeReq req = DeleteNodeReq.newBuilder()
                .setToken(this.token)
                .setNodeId(nodeId)
                .build();
        DeleteNodeResponse response = stub.deleteNode(req);
        return response.getNode();
    }

    public NetworkEdge createEdge(NetworkEdge edge) {
        CreateEdgeReq req = CreateEdgeReq.newBuilder()
                .setToken(token)
                .setEdge(edge)
                .build();
        CreateEdgeResponse response = stub.createEdge(req);
        return response.getEdge();
    }

    public GraphDTO getGraph() {
        NetworkGraphRequest request = NetworkGraphRequest.newBuilder()
                .setToken(token)
                .build();
        NetworkGraphResponse response = stub.getGraph(request);
        List<NetworkNode> nodesList = response.getNodesList();
        List<NetworkEdge> edgesList = response.getEdgesList();
        return new GraphDTO(nodesList, edgesList);
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public BridgeDbConnection getConnection() {
        return connection;
    }

    public void setConnection(BridgeDbConnection connection) {
        this.connection = connection;
    }

    public void setStub(QueryServiceGrpc.QueryServiceBlockingStub stub) {
        this.stub = stub;
    }
}
