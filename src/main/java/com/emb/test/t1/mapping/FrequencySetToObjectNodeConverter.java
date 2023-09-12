package com.emb.test.t1.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Класс-маппер для преобразования результирующей последовательности
 * {@code LinkedHashSet<Map.Entry<Character, Integer>>} к сериализуемому типу {@code ObjectNode}
 * @see ObjectNode
 */
public class FrequencySetToObjectNodeConverter implements Converter<LinkedHashSet<Map.Entry<Character, Integer>>, ObjectNode> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ObjectNode convert(MappingContext<LinkedHashSet<Map.Entry<Character, Integer>>, ObjectNode> context) {
        var json = objectMapper.createObjectNode();
        context.getSource().forEach(entry -> json.put(entry.getKey().toString(), entry.getValue()));
        return json;
    }
}
