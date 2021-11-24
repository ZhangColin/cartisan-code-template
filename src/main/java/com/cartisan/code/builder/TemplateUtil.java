package com.cartisan.code.builder;

import com.cartisan.code.domain.EntityData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author colin
 */
public class TemplateUtil {
    public static Template loadTemplate(String path, String ftl) throws IOException {
        final Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDirectoryForTemplateLoading(new File(path));
        configuration.setDefaultEncoding("utf-8");

        final Template template = configuration.getTemplate(ftl);
        return template;
    }

    public static void writer(Template template, EntityData entityData, String file) throws IOException, TemplateException {
        final FileWriter fileWriter = new FileWriter(file);
        template.process(entityData, fileWriter);
        fileWriter.close();
    }
}
