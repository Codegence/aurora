package com.codegence.aurora.server;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lmorganti on 01/03/16.
 */
@Data
public class ServerInDTO {
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String url;
}
