package com.codegence.aurora.game;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by lmorganti on 07/03/16.
 */
@Service
public class GameService {
    @Autowired
    private GameRopository gameRopository;
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    @Value("${amazon.sqs.name}")
    private String QUEUE_NAME;

    private void putSQS(Game newGame) {
        queueMessagingTemplate.convertAndSend(QUEUE_NAME, newGame);
    }

    public void saveGame(Game newGame, Game dto) {
        BeanUtils.copyProperties(dto, newGame);
        newGame.setState("start");
        Game gameSaved = gameRopository.save(newGame);
        putSQS(gameSaved);
    }

}
