package com.codegence.aurora.player;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lmorganti on 23/02/16.
 */
@Data
public class PlayerInDTO {
    @NotEmpty
    private String nickName;
}
