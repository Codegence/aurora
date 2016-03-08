package com.codegence.aurora.server;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by lmorganti on 29/02/16.
 */
@Service
public class ServerService {
    @Autowired
    private ServerRepository serverRepository;

    public Page<Server> listServerByPlayer(String playerID, Pageable page) {
        return serverRepository.findAllByPlayerID(playerID, page);
    }

    public void saveServer(Server newServer, Server dto) {
        BeanUtils.copyProperties(dto, newServer);
        serverRepository.save(newServer);
    }

    public Server getServer(String serverID) {
        Server server;
        try {
            server = serverRepository.findByServerID(serverID);
        } catch (EmptyResultDataAccessException e) {
            server = null;
        }
        return server;
    }
}
