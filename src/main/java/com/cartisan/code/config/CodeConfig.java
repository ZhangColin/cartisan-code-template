package com.cartisan.code.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author colin
 */
@Configuration
@ConfigurationProperties("code")
@Data
public class CodeConfig {
    private String serviceName;

    @Value("${code.package}")
    private String packageName;

    private List<String> tableNames;
}
