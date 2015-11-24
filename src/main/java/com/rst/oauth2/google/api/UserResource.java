package com.rst.oauth2.google.api;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rst.oauth2.google.security.GoogleProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Component
@RequestMapping("find")
public class UserResource {
    private static Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    private static ObjectMapper OM = new ObjectMapper();
    @Autowired
    private OAuth2RestOperations oauth2RestTemplate;
    private DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(new BasicAWSCredentials("AKIAIZ4A5YCDSDZQOROA",
            "a/O34mbxjGQ+dDAiWpOX99WqyK21OPo8+9JSrsh5")));
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private String playersTable = "cgPlayers";

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<String> findUsersStartingWithPrefix(@RequestParam("term") String usernamePrefix) throws JsonProcessingException {
        LOGGER.info("Searching for users starting with {}", usernamePrefix);
        List<String> list = newArrayList("user@email.com", "user2@email.com");

        GoogleProfile profile = getGoogleProfile();
        saveData(profile);
        LOGGER.info("Google Profile Data {}", OM.writerWithDefaultPrettyPrinter().writeValueAsString(profile));
        list.add(profile.getEmail());

        LOGGER.info("Returning users {}", list);
        return list;
    }

    private GoogleProfile getGoogleProfile() {
        String url = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + oauth2RestTemplate.getAccessToken();
        ResponseEntity<GoogleProfile> forEntity = oauth2RestTemplate.getForEntity(url, GoogleProfile.class);
        return forEntity.getBody();
    }

    private void saveData(GoogleProfile profile) {
        Table players = dynamoDB.getTable(playersTable);
        Map<String, String> expressionAttributeNames = new HashMap<String, String>();
        expressionAttributeNames.put("#N", "nombre");
        Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
        expressionAttributeValues.put(":val1", profile.getName());
        players.updateItem(
                new PrimaryKey("playerID", new BigDecimal(profile.getId())),
                "set #N = :val1",
                expressionAttributeNames,
                expressionAttributeValues);
//        try {
//            Item item = new Item()
//                    .withPrimaryKey("playerID", 1)
//                    .withString("nombre", profile.getName());
//            players.putItem(item);
//        } catch (Exception e) {
//            LOGGER.error("Ocurrio un error al guardar ", e);
//        }
    }
}
