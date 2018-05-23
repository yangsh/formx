package com.weimi.formx.common.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by yangsh on 2018-05-16
 */
@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper jsonMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true); // 字段名是否加双引号
        objectMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, false); // 数值是否加双引号
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // null 输出空字符串
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeString("");
            }
        });

        return objectMapper;
    }

}
