package com.admin.generator.model.constant;

/**
 * Created by ZSW on 2019/4/5.
 */
public enum TemplateEnum {
    /**
     *
     */
    DAO("/business/template/model/dao.vm","dao","Dao.java"),
    ENTITY("/business/template/model/entity.vm","entity","Entity.java"),
    XML("/business/template/model/xml.vm","dao","Dao.xml"),
    DOJO("/business/template/model/dojo.vm","dojo","Dojo.java")
    ;

    TemplateEnum(String path,String packageName,String file){
        this.template = path;
        this.packageName = packageName;
        this.file = file;
    }

    public static String getFilePath(String packageName,String entityName,TemplateEnum templateEnum){
        String pName = packageName.replace(".","/");
        String path = pName + "/" + templateEnum.getPackageName() + "/" + entityName + templateEnum.getFile();
        return path;
    }

    private String template;
    private String packageName;
    private String file;

    public String getTemplate() {
        return template;
    }

    public void setPath(String path) {
        this.template = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
