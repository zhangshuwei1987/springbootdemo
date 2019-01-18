package com.springLearn.propertiesRelate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:custom.properties")
public class TestCustomMethodProperties {
    @Bean
    @ConfigurationProperties(prefix = "custom.type2")
    public Type2 typeAProperty() {
        return new Type2();
    }

    public class Type2 {
        String name;
        String type;
        String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    @Bean
    @ConfigurationProperties(prefix = "custom.type3")
    public Type3 typeBProperty() {
        return new Type3();
    }

    public class Type3 {
        String name;
        String type;
        String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

