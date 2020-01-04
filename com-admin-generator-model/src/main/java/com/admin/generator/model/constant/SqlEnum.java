package com.admin.generator.model.constant;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
public enum  SqlEnum {
    /**
     *
     */
    TABLES("select * from information_schema.tables where table_schema='%s'"),
    COLUMNS("select * from information_schema.COLUMNS where table_name = '%s' and table_schema = '%s'"),
    ;

    SqlEnum(String sql){
        this.sql = sql;
    }

    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
