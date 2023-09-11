package com.emb.test.t1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;
import static org.junit.jupiter.api.Assertions.*;

public class CharactersServiceTest {
    private CharactersService service;
    private static class FindCharactersFrequencyArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    of("aaaaabcccc", Map.of('a', 5, 'b', 1, 'c', 4))
            );
        }
    }

    private static class SortCharactersByFrequencyArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            var sorted = new LinkedHashSet<Map.Entry<Character, Integer>>();
            sorted.add(new AbstractMap.SimpleImmutableEntry<>('a', 5));
            sorted.add(new AbstractMap.SimpleImmutableEntry<>('b', 1));
            sorted.add(new AbstractMap.SimpleImmutableEntry<>('c', 4));

            return Stream.of(of(Map.of('a', 5, 'b', 1, 'c', 4), sorted));
        }
    }

    @BeforeEach
    public void setup() {
        service = new CharactersService();
    }

    @ParameterizedTest
    @ArgumentsSource(FindCharactersFrequencyArgumentProvider.class)
    public void findCharactersFrequency(char[] characters, Map<Character, Integer> expected) {
        var map = service.findCharactersFrequency(characters);

        assertNotNull(map);
        assertEquals(map, expected);
    }

    @ParameterizedTest
    @ArgumentsSource(SortCharactersByFrequencyArgumentProvider.class)
    public void sortCharactersByFrequency(Map<Character, Integer> map, LinkedHashSet<Map.Entry<Character, Integer>> expected) {
        var sorted = service.sortCharactersByFrequency(map);

        assertNotNull(sorted);
        /*
        Несмотря на то, что для LinkedHashSet порядок элементов гарантирован,
        при сравнении двух LinkedHashSet, порядок не имеет значения.
        Чтобы этого избежать, можно было или написать собственную структуру, наследующуюся
        от LinkedHashSet с переопределённым `equals()`, или свой компаратор, заассертив `boolean`.
        Но это бы неоправданно усложнило код, а по стоимости операций было бы приблизительно то же, что
        и приведение к строке.
         */
        assertEquals(sorted.toString(), expected.toString());

        System.out.println(sorted);
        System.out.println(expected);
    }
}
