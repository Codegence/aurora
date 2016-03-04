package com.codegence.aurora.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lmorganti on 04/03/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    @NotEmpty
    private String message;
}
