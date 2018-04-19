package com.kangyonggan.cms.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
@Data
public class ShiroUser implements Serializable {

    private Long id;

    private String username;
}
