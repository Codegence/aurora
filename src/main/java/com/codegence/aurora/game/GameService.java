package com.codegence.aurora.game;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lmorganti on 07/03/16.
 */
@Service
public class GameService {
    @Autowired
    private GameRopository gameRopository;
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    @Value("${amazon.sqs.name.genesis}")
    private String QUEUE_GENESIS;
    @Value("${amazon.sqs.name.apocalipse}")
    private String QUEUE_APOCALIPSE;

    private void putSQS(Game newGame) {
        queueMessagingTemplate.convertAndSend(QUEUE_GENESIS, newGame);
    }

    public void saveGame(Game newGame, Game dto) {
        BeanUtils.copyProperties(dto, newGame);
        newGame.setState("start");
        Game gameSaved = gameRopository.save(newGame);
        putSQS(gameSaved);
    }

    @MessageMapping("cg-sector-morfosis")
    public void updateGameSQS(GameUpdateDTO dto) throws Exception {
        Game game = gameRopository.findOne(dto.getGameID());
        if (game!=null){
            game.setState(dto.getState());
            gameRopository.save(game);
        }

    }

    @MessageMapping("cg-sector-apocalipse")
    public void finishGameSQS(GameFinishDTO dto) throws Exception {
        Game game = gameRopository.findOne(dto.getGameID());
        if (game!=null){
            game.setState("finished");
            final Optional<Faction> first = game.getFactions().stream().filter(f -> f.getName().equalsIgnoreCase(dto.getFactionWin())).findFirst();
            gameRopository.save(game);
        }

    }
}
