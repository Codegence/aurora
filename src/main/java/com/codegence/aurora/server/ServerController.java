package com.codegence.aurora.server;

import com.codegence.aurora.config.StatusCodeException;
import com.codegence.aurora.player.Player;
import com.codegence.aurora.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lmorganti on 29/02/16.
 */

@RestController
@RequestMapping(value = "api/server")
public class ServerController {
    @Autowired
    private ServerService serverService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public void addServer(OAuth2Authentication user, @RequestBody @Valid Server dto) {
        Player player = playerService.findByGoogleID(user.getPrincipal().toString());
        if (player == null) throw new StatusCodeException(HttpStatus.CONFLICT);
        dto.setPlayerID(user.getPrincipal().toString());
        serverService.saveServer(new Server(), dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Server> listServers(OAuth2Authentication user,
                                    @RequestParam(value = "index") Integer index,
                                    @RequestParam(value = "size") Integer size) {
        Player player = playerService.findByGoogleID(user.getPrincipal().toString());
        if (player == null) throw new StatusCodeException(HttpStatus.CONFLICT);
        return serverService.listServerByPlayer(player.getPlayerID(), new PageRequest(index, size));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateServer(OAuth2Authentication user, @RequestBody @Valid ServerInWhitIdDTO dto) {
        Player player = playerService.findByGoogleID(user.getPrincipal().toString());
        if (player == null) throw new StatusCodeException(HttpStatus.CONFLICT);
        Server server = serverService.getServer(dto.getServerID());
        if (server == null) throw new StatusCodeException(HttpStatus.NOT_FOUND);
        serverService.saveServer(server, dto);
    }
//    public void addServer() {
//        serverService.putSQS();
//    }
}
