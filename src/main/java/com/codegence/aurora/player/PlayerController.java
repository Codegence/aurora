package com.codegence.aurora.player;

import com.codegence.aurora.config.StatusCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by lmorganti on 16/02/16.
 */

@RestController
@RequestMapping(value = "api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public void registerPlayer(OAuth2Authentication user, @RequestBody @Valid Player dto) {
        Player player = playerService.findByGoogleID(user.getPrincipal().toString());
        if (player == null){
            dto.setGoogleID(user.getPrincipal().toString());
            playerService.savePlayer(new Player(), dto);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Player getPlayer(OAuth2Authentication user) {
        Player player = playerService.findByGoogleID(user.getPrincipal().toString());
        if (player == null) new StatusCodeException(HttpStatus.NOT_FOUND);
        return player;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updatePlayer(OAuth2Authentication user, @RequestBody @Valid Player dto) {
        Player player = playerService.findByGoogleID(user.getPrincipal().toString());
        if (player == null) new StatusCodeException(HttpStatus.NOT_FOUND);
        playerService.savePlayer(player, dto);
    }
}
