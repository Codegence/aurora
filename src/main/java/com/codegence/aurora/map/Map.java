package com.codegence.aurora.map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lmorganti on 04/03/16.
 */
@DynamoDBTable(tableName = "cgMaps")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Map {
    @NotEmpty
    private String url;
    @NotEmpty
    private String nickName;

    @DynamoDBHashKey
    public String getUrl() {
        return url;
    }

    @DynamoDBAttribute
    public String getNickName() {
        return nickName;
    }
}
