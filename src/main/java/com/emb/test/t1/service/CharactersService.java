package com.emb.test.t1.service;

import com.emb.test.t1.data.dto.CharactersSortInfo;
import com.emb.test.t1.service.counter.FrequencyComparatorFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Map;

@Service
public class CharactersService {

    private final FrequencyComparatorFactory comparatorFactory;

    public CharactersService() {
        comparatorFactory = new FrequencyComparatorFactory();
    }

    public LinkedHashSet<Map.Entry<Character, Integer>> sortedByFrequencyCharactersFromString(@NonNull CharactersSortInfo data) {
        var comparator = comparatorFactory.createComparator(data.order());
        var counter = new CharacterFrequencyCounter(comparator);
        var frequencies = counter.findCharactersFrequency(data.string().toCharArray());

        return counter.sortCharactersByFrequency(frequencies);
    }
}
