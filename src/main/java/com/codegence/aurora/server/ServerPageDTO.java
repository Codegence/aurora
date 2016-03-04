package com.codegence.aurora.server;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by lmorganti on 02/03/16.
 */
@Data
public class ServerPageDTO {
    @Min(0)
    @NotNull
    private Integer index;

    @Min(0)
    @NotNull
    private Integer size;
}
