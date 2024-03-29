package com.cartisan.code.builder;

import com.cartisan.code.domain.EntityData;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author colin
 */
public class BuilderFactory {
    public static void builder(EntityData entityData, String templatePath, String templateFile,
                               String storePath, String suffix) throws IOException, TemplateException {
        final Template template = TemplateUtil.loadTemplate(BuilderFactory.class.getResource(templatePath).getPath(), templateFile);

        final String projectPath = BuilderFactory.class.getClassLoader().getResource("")
                .getPath().replace("/target/test-classes", "").replace("/target/classes", "") + "/generate/main/java/";
        final String path = projectPath + storePath.replace(".", "/");

        final File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        TemplateUtil.writer(template, entityData, path + "/" + entityData.getPascalModule() + suffix);
    }
}
