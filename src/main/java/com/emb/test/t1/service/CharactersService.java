package com.emb.test.t1.service;

import com.emb.test.t1.data.dto.CharactersSortInfo;
import com.emb.test.t1.service.counter.FrequencyComparatorFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Сервисный класс отвечающий за логику работы с символами.
 */
@Service
public class CharactersService {

    private final FrequencyComparatorFactory comparatorFactory;

    public CharactersService() {
        comparatorFactory = new FrequencyComparatorFactory();
    }

    /**
     * Метод сортирующий символы исходной строки по частоте их использования.
     * @param data объект содержащий исходную строку для частотного анализа и порядок сортировки.
     * @return отсортированный LinkedHashSet, содержащий {@code Map.Entry<Character, Integer>} иначе говоря
     * упорядоченная последовательность пары символа с его количеством повторений в исходной строке.
     */
    public LinkedHashSet<Map.Entry<Character, Integer>> sortedByFrequencyCharactersFromString(@NonNull CharactersSortInfo data) {
        if (data.string() == null) throw new IllegalArgumentException("Input string must not be null!");
        var comparator = comparatorFactory.createComparator(data.order());
        var counter = new CharacterFrequencyCounter(comparator);
        var frequencies = counter.findCharactersFrequency(data.string().toCharArray());

        return counter.sortCharactersByFrequency(frequencies);
    }
}
