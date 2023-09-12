package com.emb.test.t1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс подсчитывающий количество повторений символа в заданной строке.
 */
public class CharacterFrequencyCounter {

    @Autowired
    private ObjectMapper mapper;
    private final Comparator<Map.Entry<Character, Integer>> comparator;

    public CharacterFrequencyCounter(Comparator<Map.Entry<Character, Integer>> comparator) {
        this.comparator = comparator;
    }

    /**
     * Находит частоту повторений символов в заданном массиве символов.
     * @param characters массив символов, частоту которых необходимо подсчитать.
     * @return {@code Map<Character, Integer>}, сопоставляющую символы к количеству их повторений
     * в исходной строке. Ключ - символ, значение - число его повторений в строке.
     */
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

    /**
     * Сортирует {@code Map<Character, Integer>} символов и их повторений.
     * @param characterFrequency словарь повторений. Ключ - символ, значение - число его повторений в строке.
     * @return отсортированный LinkedHashSet, содержащий {@code Map.Entry<Character, Integer>} иначе говоря
     * упорядоченная последовательность пары символа с его количеством повторений в исходной строке.
     */
    public LinkedHashSet<Map.Entry<Character, Integer>> sortCharactersByFrequency(Map<Character, Integer> characterFrequency) {
        Objects.requireNonNull(characterFrequency);

        return characterFrequency.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}