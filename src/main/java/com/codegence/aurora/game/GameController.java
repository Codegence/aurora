package com.codegence.aurora.game;

import com.codegence.aurora.config.StatusCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lmorganti on 04/03/16.
 */
@RestController
@RequestMapping(value = "api/game")
public class GameController {
    final static private String FILE_PATH = "victoryCondition.yml";
    @Autowired
    private GameService gameService;

    @RequestMapping(method = RequestMethod.POST)
    public void addGame(OAuth2Authentication user, @RequestBody @Valid Game dto) {
        long cUniqueFaction = dto.getFactions().stream().distinct().count();
        if (cUniqueFaction != dto.getFactions().size())
            new StatusCodeException(HttpStatus.BAD_REQUEST);
        gameService.saveGame(new Game(), dto);

    }

    @RequestMapping(value = "victoryCondition", method = RequestMethod.GET)
    public List<String> listVictoryCondition() {
        final Yaml yaml = new Yaml();
        return (List) yaml.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_PATH));
    }
}
