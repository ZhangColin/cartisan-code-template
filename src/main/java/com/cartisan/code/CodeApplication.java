package com.cartisan.code;

import com.cartisan.code.application.GeneratorService;
import freemarker.template.TemplateException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@MapperScan(basePackages = "com.cartisan.code")
public class CodeApplication implements CommandLineRunner {
    private final GeneratorService generatorService;

    public CodeApplication(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws TemplateException, IOException {
        generatorService.generateCode();
    }
}
