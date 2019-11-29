package com.github.mustfun.mybatis.plugin.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2018/5/16
 * @since 1.0
 */
@Setter
@Getter
public class DbSourcePo {

    private Integer id;
    private Integer port;
    private String userName;
    private String dbAddress;
    private String password;
    private String dbName;
    private Date createTime;
}
