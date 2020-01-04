package com.admin.generator.component.velocity;

import com.admin.generator.component.config.BaseConfig;
import com.admin.generator.model.bean.TemplateEntity;
import com.admin.generator.model.constant.TemplateEnum;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 *
 * @author ZSW
 * @date 2018/12/20
 */
public class VelocityFactory {

    private static final String KEY = "data";

    /**
     * 获取模板
     * @return
     */
    private static Template getTemplate(String templateName) throws Exception{
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template template = ve.getTemplate(templateName, BaseConfig.DEFAULT_ENCODING);
        if(template == null){
            throw new RuntimeException("模板不存在");
        }
        return template;
    }

    /**
     * 执行
     * @param entity
     * @param zip
     * @param templateEnum
     * @throws Exception
     */
    public static void execute(TemplateEntity entity, ZipOutputStream zip, TemplateEnum templateEnum) throws Exception{
        VelocityContext ctx = new VelocityContext();
        ctx.put(KEY,entity);
        StringWriter sw = new StringWriter();
        getTemplate(templateEnum.getTemplate()).merge(ctx,sw);
        String s = sw.toString();
        String entryName = TemplateEnum.getFilePath(entity.getPackageName(),entity.getEntityName(),templateEnum);
        zip.putNextEntry(new ZipEntry(entryName));

        IOUtils.write(s,zip);
    }
}
