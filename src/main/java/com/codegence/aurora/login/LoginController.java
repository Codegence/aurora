package com.codegence.aurora.login;

import com.codegence.aurora.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lmorganti on 29/02/16.
 */
@RestController
public class LoginController {
    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public boolean isRegistred(OAuth2Authentication user, HttpServletResponse response) throws IOException, HttpStatusCodeException {
        if (user == null) {
            response.sendError(HttpStatus.FORBIDDEN.value());
            return Boolean.parseBoolean(null);
        }
        return playerService.findByGoogleID(user.getPrincipal().toString()) != null;

    }
}
