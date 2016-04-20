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
    private boolean isWinner;
    private Long score;

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

    @DynamoDBAttribute
    public boolean getIsWinner() {
        return isWinner;
    }

    @DynamoDBAttribute
    public Long getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faction faction = (Faction) o;

        return !(name != null ? !name.equalsIgnoreCase(faction.name) : faction.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
