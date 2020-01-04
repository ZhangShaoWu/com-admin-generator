package com.admin.generator.model.bean;

import lombok.Data;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
@Data
public class TableEntity {

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;
    private String engine;
    private String version;
    private String rowFormat;
    private String tableRows;
    private String avgRowLength;
    private String dataLength;
    private String maxDataLength;
    private String indexLength;
    private String dataFree;
    private String autoIncrement;
    private String createTime;
    private String updateTime;
    private String checkTime;
    private String tableCollation;
    private String checksum;
    private String createOptions;
    private String tableComment;


}
