package com.admin.generator.model.constant;



import com.admin.framework.component.utils.StringUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ZSW
 * @date 2019/3/3
 */
public enum TypeEnum {

    /**
     * 字符串
     */
    STRING(1,"String","char,varchar,longtext,text",String.class),
    /**
     * int
     */
    INTEGER(2,"Integer","int,tinyint",Integer.class),

    /**
     * long
     */
    LONG(2,"Long","bigint",Integer.class),
    /**
     * 时间
     */
    DATE(3,"Date","time,datetime",Date.class),

    /**
     * 金额
     */
    DECIMAL(4,"BigDecimal","decimal",BigDecimal.class),

    /**
     * double
     */
    DOUBLE(5,"Double","double",Double.class);

    TypeEnum(Integer key, String javaTypeName, String sqlTypeName, Class type){
        this.key = key;
        this.javaTypeName = javaTypeName;
        this.sqlTypeName = sqlTypeName;
        this.type = type;

    }


    private Integer key;
    private String javaTypeName;
    private String sqlTypeName;
    private Class type;

    public static String getByJavaTypeName(String javaType){
        for(TypeEnum typeEnum:values()){
            String sqlTypeName = typeEnum.getJavaTypeName();
            if(sqlTypeName.equals(javaType)){
                return typeEnum.getJavaTypeName();
            }
        }
        return null;
    }

    /**
     * 根据sqltype获取java类型
     * @param sqlType
     * @return
     */
    public static String getJavaTypeBySqlTypeName(String sqlType){

        for(TypeEnum typeEnum:values()){
            String sqlTypeName = typeEnum.getSqlTypeName();
            List<String> strings = StringUtil.toList(sqlTypeName, ",");
            if(strings.contains(sqlType)){
                return typeEnum.getJavaTypeName();
            }
        }
        return null;
    }


    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getJavaTypeName() {
        return javaTypeName;
    }

    public void setJavaTypeName(String javaTypeName) {
        this.javaTypeName = javaTypeName;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getSqlTypeName() {
        return sqlTypeName;
    }

    public void setSqlTypeName(String sqlTypeName) {
        this.sqlTypeName = sqlTypeName;
    }
}
