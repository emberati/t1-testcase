package com.emb.test.t1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

public class CharacterFrequencyCounter {

    @Autowired
    private ObjectMapper mapper;
    private final Comparator<Map.Entry<Character, Integer>> comparator;

    public CharacterFrequencyCounter(Comparator<Map.Entry<Character, Integer>> comparator) {
        this.comparator = comparator;
    }

    public Map<Character, Integer> findCharactersFrequency(char[] characters) {
        Objects.requireNonNull(characters);
        if (characters.length == 0) throw new IllegalArgumentException();
        var frequencies = new HashMap<Character, Integer>();
        for (char character : characters) {
            int count = 1;
            if (frequencies.containsKey(character)) {
                count += frequencies.get(character);
            }
            frequencies.put(character, count);
        }
        return frequencies;
    }

    public LinkedHashSet<Map.Entry<Character, Integer>> sortCharactersByFrequency(Map<Character, Integer> characterFrequency) {
        Objects.requireNonNull(characterFrequency);

        return characterFrequency.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}