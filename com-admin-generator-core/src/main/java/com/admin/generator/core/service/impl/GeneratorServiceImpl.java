package com.admin.generator.core.service.impl;

import com.admin.framework.common.entity.Page;
import com.admin.framework.common.entity.Resp;
import com.admin.framework.component.annotations.ParamAnnotation;
import com.admin.framework.component.annotations.ParamField;
import com.admin.framework.component.utils.MapUtil;
import com.admin.framework.component.utils.ReflectUtil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.generator.component.database.TableFactory;
import com.admin.generator.component.utils.GeneratorUtil;
import com.admin.generator.core.service.GeneratorService;
import com.admin.generator.model.bean.DatabaseEntity;
import com.admin.generator.model.bean.GenFormVo;
import com.admin.generator.model.bean.TemplateEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @Author ZSW
 * @Date 2019/4/5
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Override
    public Resp listTables(Map param) {
        DatabaseEntity databaseEntity = ReflectUtil.mapToBean(param, DatabaseEntity.class);
        Integer pageSize = MapUtil.getInteger(param, "pageSize");
        Integer pageNumber = MapUtil.getInteger(param, "currentPage");
        String tableName = MapUtil.getString(param, "tableName");
        try {
            TableFactory tableFactory = new TableFactory(databaseEntity);
            Page page = new Page(pageSize,pageNumber);
            Page tables = tableFactory.getTables(databaseEntity.getDbName(), page, tableName);
            return Resp.success(tables);
        } catch (Exception e) {
            e.printStackTrace();
            return Resp.error(e.getMessage());
        }
    }

    @Override
    public GenFormVo gen(Map param) throws Exception {
        String tNames = MapUtil.getString(param,"tableNames");
        String[] tableNames = null;
        if(!StringUtil.isEmpty(tNames)){
            tableNames = tNames.split(",");
        }
        DatabaseEntity databaseEntity = ReflectUtil.mapToBean(param, DatabaseEntity.class);
        TemplateEntity templateEntity = ReflectUtil.mapToBean(param, TemplateEntity.class);
        return GeneratorUtil.gen(databaseEntity, templateEntity,tableNames);
    }
}
