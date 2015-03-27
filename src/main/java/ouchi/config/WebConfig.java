package ouchi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
//    @Bean
//    public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean() {
//        Jackson2ObjectMapperFactoryBean objectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
//        //objectMapperFactoryBean.setFeaturesToDisable(WRITE_DATES_AS_TIMESTAMPS);
//        return objectMapperFactoryBean;
//    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // customize
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JSR310Module());
        return objectMapper;
    }
}
