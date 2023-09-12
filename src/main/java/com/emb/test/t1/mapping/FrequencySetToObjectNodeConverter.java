package com.emb.test.t1.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Map;

public class FrequencySetToObjectNodeConverter implements Converter<LinkedHashSet<Map.Entry<Character, Integer>>, ObjectNode> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ObjectNode convert(MappingContext<LinkedHashSet<Map.Entry<Character, Integer>>, ObjectNode> context) {
        var json = objectMapper.createObjectNode();
        context.getSource().forEach(entry -> json.put(entry.getKey().toString(), entry.getValue()));
        return json;
    }
}
