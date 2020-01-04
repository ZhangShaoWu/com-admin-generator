package com.admin.generator.component.database;

import com.admin.framework.common.entity.Page;
import com.admin.framework.component.utils.*;
import com.admin.generator.model.bean.ColumnEntity;
import com.admin.generator.model.bean.DatabaseEntity;
import com.admin.generator.model.bean.TableEntity;
import com.admin.generator.model.constant.SqlEnum;
import com.admin.generator.model.constant.TypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
public class TableFactory<T> {


    private JDBC jdbc;

    public TableFactory(DatabaseEntity databaseEntity) throws Exception {
        if(jdbc == null){
            jdbc = new JDBC(databaseEntity);
        }
    }



    /**
     * 获取所有表
     * @param dbName
     * @return
     */
    public List<TableEntity> getTables(String dbName, String... tableName) throws Exception{
        String sql = getAllTableSql(dbName, tableName);
        String executeSql = sql;
        List<Map> data = jdbc.getData(executeSql);
        if(ListUtil.isEmpty(data)){
            throw new RuntimeException("数据库【"+dbName+"】里面没有表");
        }
        List<TableEntity> result = new ArrayList<>();
        for(Map map : data){
            TableEntity tableEntity = JSONUtil.mapToObj(map, TableEntity.class);
            result.add(tableEntity);
        }
        return result;
    }

    /**
     * 获取所有表
     * @param dbName
     * @return
     */
    public Page<TableEntity> getTables(String dbName, Page page, String tableName) throws Exception{
        String sql = queryTableSql(dbName, tableName);
        Integer total = getTotal(sql);
        String executeSql = sql + " LIMIT "+ ((page.getCurrentPage()-1)*page.getPageSize()) +","+ page.getPageSize() +"";
        List<Map> data = jdbc.getData(executeSql);
        if(ListUtil.isEmpty(data)){
//            throw new RuntimeException("数据库【"+dbName+"】里面没有表");
            return page;
        }
        List<TableEntity> result = new ArrayList<>();
        for(Map map : data){
            TableEntity tableEntity = JSONUtil.mapToObj(map, TableEntity.class);
            result.add(tableEntity);
        }
        page.setTotal(total);
        page.setTotalPage(total,page.getPageSize());
        page.setData(result);
        return page;
    }


    /**
     * 获取所有表字段
     * @param tableName
     * @return
     */
    public List<ColumnEntity> getColumns(String  tableName, String dbName) throws Exception{
        String baseSql = SqlEnum.COLUMNS.getSql();
        String sql = String.format(baseSql,tableName,dbName);
        List<Map> data = jdbc.getData(sql);
        if(ListUtil.isEmpty(data)){
            throw new RuntimeException("表【"+tableName+"】不存在");
        }
        List<ColumnEntity> result = new ArrayList<>();
        for(Map map : data){
            ColumnEntity columnEntity = JSONUtil.mapToObj(map, ColumnEntity.class);
            String dataType = columnEntity.getDataType();
            String javaType = TypeEnum.getJavaTypeBySqlTypeName(dataType);
            String columnName = columnEntity.getColumnName();
            String cName = StringUtil.underToHump(columnName);
            columnEntity.setFieldName(cName);
            String methodName = cName.substring(0, 1).toUpperCase() + cName.substring(1, cName.length());
            columnEntity.setMethodName(methodName);
            columnEntity.setJavaType(javaType);

            String extra = columnEntity.getExtra() == null ? "" : columnEntity.getExtra().toLowerCase();
            String columnDefault = columnEntity.getColumnDefault() == null ? "" : columnEntity.getColumnDefault().toLowerCase();
            if("auto_increment".equals(extra) || "on update current_timestamp".equals(extra)){
                columnEntity.setInsert(false);
                columnEntity.setUpdate(false);
            }

            if(!StringUtil.isEmpty(columnDefault)){
                if("current_timestamp".equals(columnDefault)){
                    columnEntity.setUpdate(false);
                    columnEntity.setInsert(false);
                }
            }
            result.add(columnEntity);
        }
        return result;
    }

    /**
     * 获取sql
     * @param dbName
     * @param tableName
     * @return
     */
    private String getAllTableSql(String dbName,String... tableName){
        String sql = String.format(SqlEnum.TABLES.getSql(), dbName);
        if(!ArrayUtil.isEmpty(tableName)){
            String s = ArrayUtil.joinForSql(tableName, ",");
            sql =  sql + " and `TABLE_NAME` in ( "+ s +" )";
        }
        return sql;
    }

    /**
     * 获取sql
     * @param dbName
     * @param tableName
     * @return
     */
    private String queryTableSql(String dbName,String tableName){
        String sql = String.format(SqlEnum.TABLES.getSql(), dbName);
        if(!StringUtil.isEmpty(tableName)){
            sql =  sql + " and `TABLE_NAME` LIKE '%"+ tableName +"%' ";
        }
        return sql;
    }


    /**
     * 获取总数
     * @param sql
     * @return
     * @throws Exception
     */
    private Integer getTotal(String sql) throws Exception {
        String countSql = "select count(1) c from (" + sql + ") s";
        List<Map> data = jdbc.getData(countSql);
        Map map = data.get(0);
        return MapUtil.getInteger(map,"c");
    }
}
