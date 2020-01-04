package com.admin.generator.core.service;

import com.admin.framework.common.entity.Resp;
import com.admin.generator.model.bean.GenFormVo;

import java.util.Map;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
public interface GeneratorService {

    /**
     * 获取所有的表
     * @param param
     * @return
     */
    Resp listTables(Map param);

    /**
     * 生成sql
     * @param param
     * @return
     * @throws Exception
     */
    GenFormVo gen(Map param) throws Exception;

}
