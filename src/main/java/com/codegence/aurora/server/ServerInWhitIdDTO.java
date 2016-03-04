package com.codegence.aurora.server;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lmorganti on 04/03/16.
 */
@Data
public class ServerInWhitIdDTO extends ServerInDTO {
    @NotEmpty
    private String serverID;
}
