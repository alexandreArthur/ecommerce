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
    private Long timestamp;
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private String error;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String path;



}
