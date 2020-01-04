package com.admin.generator.model.bean;

import com.admin.framework.component.annotations.NotNull;
import com.admin.framework.component.utils.DateUtil;
import com.admin.generator.model.bean.ColumnEntity;
import lombok.Data;

import java.util.List;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
@Data
public class TemplateEntity {

    private boolean lombok = false;

    private String baseDaoPath;
    private String springBeanUtilPath;
    private String baseDojoPath;

    @NotNull(message = "请指定包名")
    private String packageName;
    private String author = "admin";
    private String date = DateUtil.getCurrentDateFormat(DateUtil.USUAL_FORMAT);


    private String tableComment;
    private String tableName;
    private String entityName;
    private String daoName;
    private List<ColumnEntity> columns;
    private boolean dateFlag;
    private boolean decimalFlag;
    private String alias;
    private String idType;

}
