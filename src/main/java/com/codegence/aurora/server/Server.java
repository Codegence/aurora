package com.codegence.aurora.server;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lmorganti on 19/02/16.
 */
@DynamoDBTable(tableName = "cgServers")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    private String serverID;
    private String nick;
    private String url;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getServerID() {
        return serverID;
    }

    @DynamoDBAttribute
    public String getNick() {
        return nick;
    }

    @DynamoDBAttribute
    public String getUrl() {
        return url;
    }
}