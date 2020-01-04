package com.admin.generator.controller;

import com.admin.framework.common.entity.Resp;
import com.admin.framework.component.annotations.ParamAnnotation;
import com.admin.framework.component.annotations.ParamField;
import com.admin.framework.component.utils.MapUtil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.generator.component.config.BaseConfig;
import com.admin.generator.core.service.GeneratorService;
import com.admin.generator.model.bean.GenFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ZSW
 * @date 2019/3/28
 */
@Controller
@RequestMapping("/generator/")
public class BaseController {
    @Autowired
    private GeneratorService generatorService;



    @GetMapping("tables")
    @ResponseBody
    @ParamAnnotation(fields = {
            @ParamField(name = "pageSize",type = Integer.class),
            @ParamField(name = "currentPage",type = Integer.class)
    })
    public Resp listTables(@RequestParam Map param){
        return generatorService.listTables(param);
    }

    @RequestMapping("gen")
    public void gen(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String[]> paramMap = request.getParameterMap();
        Map param = new HashMap();
        paramMap.forEach((k,v)->{
            param.put(k,v[0]);
        });

        GenFormVo vo = generatorService.gen(param);
        byte[] bytes = vo.getBytes();
        response.setContentType("application/force-download");
        String attachment = "attachment; filename=" + vo.getTableName() + ".zip";
        response.reset();
        response.setHeader("Content-Disposition", attachment);
        response.addHeader("Content-Length", "" + bytes.length);
        response.setContentType("application/octet-stream; charset="+ BaseConfig.DEFAULT_ENCODING);
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
    }

}
