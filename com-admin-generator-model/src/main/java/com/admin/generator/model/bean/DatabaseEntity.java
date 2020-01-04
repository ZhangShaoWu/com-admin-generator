package com.admin.generator.model.bean;

import com.admin.framework.component.annotations.NotNull;
import lombok.Data;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
@Data
public class DatabaseEntity {

    private final String URL_BASE = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    /**
     * /URL指向要访问的数据库名data
     */
    private String url;

    @NotNull(message = "数据库主机地址不能为空")
    private String host;

    @NotNull(message = "数据访问端口不能为空")
    private Integer port;

    /**
     * /MySQL配置时的用户名
     */
    @NotNull(message = "数据库访问用户名不能为空")
    private String user;
    /**
     * /MySQL配置时的密码
     */
    @NotNull(message = "数据库访问密码不能为空")
    private String password;

    /**
     * 数据库名称
     */
    @NotNull(message = "数据库名称不能为空")
    private String dbName;


    public String getUrl(){
        return String.format(URL_BASE,host,port,dbName);
    }

}
