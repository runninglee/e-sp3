package com.julan.sp3.config;

import com.julan.sp3.util.factory.YmlConfigFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Data
@Component
@ConfigurationProperties(prefix = "custom")
@PropertySource(value = "classpath:/config/custom.yml", factory = YmlConfigFactory.class)
@Configuration
public class CustomConfig {

    public String Name;

    public List<String> author;

    public List<Map<String, String>> users;
}
