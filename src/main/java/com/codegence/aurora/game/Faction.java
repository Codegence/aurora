package com.codegence.aurora.game;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lmorganti on 04/03/16.
 */
@Setter
@NoArgsConstructor
@DynamoDBDocument
public class Faction {
    @NotEmpty
    private String serverID;
    @NotEmpty
    private String url;
    @NotEmpty
    private String name;

    @DynamoDBAttribute
    public String getServerID() {
        return serverID;
    }

    @DynamoDBAttribute
    public String getUrl() {
        return url;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }
}
