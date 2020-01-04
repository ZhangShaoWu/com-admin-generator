package com.admin.generator.model.bean;

import lombok.Data;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
@Data
public class ColumnEntity {

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private String methodName;
    private String fieldName;
    private String ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String javaType;
    private String characterMaximumLength;
    private String characterOctetLength;
    private String numericPrecision;
    private String numericScale;
    private String datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String columnComment;
    private String generationExpression;
    private boolean insert = true;
    private boolean update = true;



}
