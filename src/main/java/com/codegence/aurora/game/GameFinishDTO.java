package com.codegence.aurora.game;

import lombok.Data;

import java.util.List;

/**
 * Created by lmorganti on 10/03/16.
 */
@Data
public class GameFinishDTO {
    private String gameID;
    private String factionWin;
    private List<FactionScoreDTO> factionScore;
}
