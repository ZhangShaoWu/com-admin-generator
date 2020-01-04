package com.admin.generator.component.utils;

import com.admin.framework.component.utils.DateUtil;
import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.NotNullVerifyUtil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.generator.component.database.TableFactory;
import com.admin.generator.model.bean.*;
import com.admin.generator.component.velocity.VelocityFactory;
import com.admin.generator.model.constant.TemplateEnum;
import com.admin.generator.model.constant.TypeEnum;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
public class GeneratorUtil<T> {

    /**
     * 生成
     * @param databaseEntity
     * @param tableNames
     * @return
     * @throws Exception
     */
    public static GenFormVo gen(DatabaseEntity databaseEntity, TemplateEntity templateEntity, String... tableNames) throws Exception {
        List<String> verify = NotNullVerifyUtil.verify(templateEntity);
        if(!ListUtil.isEmpty(verify)){
            throw new RuntimeException(verify.get(0));
        }
        TableFactory tableFactory = new TableFactory(databaseEntity);
        String dbName = databaseEntity.getDbName();
        List<TableEntity> tableEntities = tableFactory.getTables(dbName,tableNames);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);
        for(TableEntity table : tableEntities) {
            String tableName = table.getTableName();
            List<ColumnEntity> columns = tableFactory.getColumns(tableName,dbName);

            String alias = tableName.substring(0,1);
            templateEntity.setAlias(alias);
            String tName = StringUtil.underToHump(tableName);
            String entityName = tName.substring(0, 1).toUpperCase() + tName.substring(1, tName.length());
            templateEntity.setEntityName(entityName);
            templateEntity.setDaoName(tName);
            templateEntity.setTableName(tableName);
            templateEntity.setColumns(columns);
            templateEntity.setTableComment(table.getTableComment());
            templateEntity.setDate(DateUtil.getCurrentDateFormat(DateUtil.USUAL_FORMAT));

            Special special = getSpecial(columns);

            templateEntity.setDateFlag(special.isDate());
            templateEntity.setDecimalFlag(special.isDecimal());
            templateEntity.setIdType(special.getIdType());

            VelocityFactory.execute(templateEntity,zip,TemplateEnum.DOJO);
            VelocityFactory.execute(templateEntity,zip,TemplateEnum.DAO);
            VelocityFactory.execute(templateEntity,zip,TemplateEnum.XML);
            VelocityFactory.execute(templateEntity,zip,TemplateEnum.ENTITY);
        }
        IOUtils.closeQuietly(zip);
        TableEntity tableEntity = tableEntities.get(0);
        String tableName = tableEntity.getTableName();

        GenFormVo genFormVo = new GenFormVo();
        genFormVo.setBytes(out.toByteArray());
        genFormVo.setTableName(tableName);
        return genFormVo;
    }


    /**
     * 是否包含时间
     * @param columns
     * @return
     */
    private static Special getSpecial(List<ColumnEntity> columns){
        Special special = new Special();
        for(ColumnEntity column:columns){
            String javaType = column.getJavaType();
            if(TypeEnum.DATE.getJavaTypeName().equals(javaType)){
                special.setDate(true);
            }
            if(TypeEnum.DECIMAL.getJavaTypeName().equals(javaType)){
                special.setDecimal(true);
            }
            String columnKey = column.getColumnKey();
            if("PRI".equals(columnKey)){
                special.setIdType(javaType);
            }
        }
        return special;
    }

    static class Special{
        private boolean decimal;
        private boolean date;
        private String idType;

        public boolean isDecimal() {
            return decimal;
        }

        public Special setDecimal(boolean decimal) {
            this.decimal = decimal;
            return this;
        }

        public boolean isDate() {
            return date;
        }

        public Special setDate(boolean date) {
            this.date = date;
            return this;
        }

        public String getIdType() {
            return idType;
        }

        public Special setIdType(String idType) {
            this.idType = idType;
            return this;
        }
    }

}
