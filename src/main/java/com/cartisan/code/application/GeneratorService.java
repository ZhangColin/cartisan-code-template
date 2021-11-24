package com.cartisan.code.application;

import com.cartisan.code.builder.BuilderFactory;
import com.cartisan.code.config.CodeConfig;
import com.cartisan.code.domain.ColumnEntity;
import com.cartisan.code.domain.EntityData;
import com.cartisan.code.domain.FieldData;
import com.cartisan.code.domain.TableEntity;
import com.cartisan.code.mapper.MySqlMapper;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class GeneratorService {
    private final CodeConfig codeConfig;
    private final MySqlMapper mapper;

    public GeneratorService(CodeConfig codeConfig, MySqlMapper mapper) {
        this.codeConfig = codeConfig;
        this.mapper = mapper;
    }

    public void generateCode() throws TemplateException, IOException {
        List<String> tableNames = codeConfig.getTableNames();
        if (tableNames == null || tableNames.size() == 0) {
            tableNames = mapper.getAllTableNames().stream().filter(tableName -> !tableName.startsWith("schema_")).collect(toList());
        }

        for (String tableName : tableNames) {
            final TableEntity table = mapper.getTable(tableName);
            final List<ColumnEntity> columns = mapper.getColumns(tableName);

            final EntityData entityData =
                    new EntityData(codeConfig.getServiceName(), codeConfig.getPackageName(), table, columns.stream().map(FieldData::new).collect(toList()));

            BuilderFactory.builder(entityData, "/template/code", "Controller.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "Controller.java");

            BuilderFactory.builder(entityData, "/template/code", "AppService.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "AppService.java");

            BuilderFactory.builder(entityData, "/template/code", "Converter.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "Converter.java");

            BuilderFactory.builder(entityData, "/template/code", "DomainModel.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    ".java");

            BuilderFactory.builder(entityData, "/template/code", "Dto.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "Dto.java");

            BuilderFactory.builder(entityData, "/template/code", "Param.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "Param.java");

            BuilderFactory.builder(entityData, "/template/code", "Query.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "Query.java");

            BuilderFactory.builder(entityData, "/template/code", "Repository.java.ftl",
                    ("/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "Repository.java");

            BuilderFactory.builder(entityData, "/template/api", "Api.http",
                    ("/api/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    ".Http");

            BuilderFactory.builder(entityData, "/template/vue", "List.vue.ftl",
                    ("/vue/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    ".vue");

            BuilderFactory.builder(entityData, "/template/vue", "Form.vue.ftl",
                    ("/vue/" + entityData.getPackageName() + "/" + entityData.getPascalModule()).replace(".", "/").toLowerCase(),
                    "Form.vue");
        }

    }
}
