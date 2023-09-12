package com.emb.test.t1.configuration;

import com.emb.test.t1.mapping.FrequencySetToObjectNodeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.addConverter(new FrequencySetToObjectNodeConverter());
        return mapper;
    }
}
