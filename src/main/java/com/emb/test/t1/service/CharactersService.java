package com.emb.test.t1.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharactersService {
    public Map<Character, Integer> findCharactersFrequency(char[] characters) {
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

    public Set<Map.Entry<Character, Integer>> sortCharactersByFrequency(Map<Character, Integer> characterFrequency) {
        return characterFrequency.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
