package com.ecommerce.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private Long timeStamp;

}
