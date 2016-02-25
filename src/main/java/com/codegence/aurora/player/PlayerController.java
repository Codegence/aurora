package com.codegence.aurora.player;

import com.amazonaws.util.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by lmorganti on 16/02/16.
 */

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    private Player getPlayer(OAuth2Authentication user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject((Map) user.getUserAuthentication().getDetails());
        GoogleProfile gProfile = mapper.readValue(jsonObject.toString(), GoogleProfile.class);
        return playerService.findByGoogleID(gProfile.getId());
    }

    @RequestMapping(value = "api/register", method = RequestMethod.POST)
    public void register(OAuth2Authentication user, @RequestBody @Valid PlayerInDTO playerInDTO) throws IOException {
        Player player = getPlayer(user);
        if (player == null) {
            player = new Player();
            player.setNickName(playerInDTO.getNickName());
            player.setGoogleID(player.getGoogleID());
            playerService.save(player);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public boolean isRegistred(OAuth2Authentication user,HttpServletResponse response) throws IOException, HttpStatusCodeException {
        if (user==null){
            response.sendError(HttpStatus.FORBIDDEN.value());
            return Boolean.parseBoolean(null);
        }
        return getPlayer(user) != null;

    }

    @RequestMapping(value = "/message")
    public Map<String, Object> mess() {
        return Collections.<String, Object>singletonMap("message", "Yay!");
    }


}
