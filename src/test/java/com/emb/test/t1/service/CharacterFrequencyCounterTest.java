package com.emb.test.t1.service;

import com.emb.test.t1.service.counter.FrequencyComparatorFactory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

public class CharacterFrequencyCounterTest {
    private static class FindCharactersFrequencyArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            var comparatorFactory = new FrequencyComparatorFactory();
            var counterDescending = new CharacterFrequencyCounter(
                    comparatorFactory.createComparator(FrequencyComparatorFactory.SortingStrategy.DESCENDING));
            return Stream.of(
                    of(counterDescending, "aaaaabcccc".toCharArray(), Map.of('a', 5, 'b', 1, 'c', 4)),
                    of(counterDescending, "xxxyyyzzz".toCharArray(), Map.of('x', 3, 'y', 3, 'z', 3)),
                    of(counterDescending, "".toCharArray(), Map.of())
            );
        }
    }

    private static class SortCharactersByFrequencyArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            var comparatorFactory = new FrequencyComparatorFactory();

            var counterDescending = new CharacterFrequencyCounter(
                    comparatorFactory.createComparator(FrequencyComparatorFactory.SortingStrategy.DESCENDING));
            var counterAscending = new CharacterFrequencyCounter(
                    comparatorFactory.createComparator(FrequencyComparatorFactory.SortingStrategy.ASCENDING));

            var testcase1 = Map.of('a', 5, 'b', 1, 'c', 4);
            var testcase2 = Map.of('x', 3, 'y', 3, 'z', 3);
            var testcase3 = Map.of();

            var descendingExpectation1 = new LinkedHashSet<Map.Entry<Character, Integer>>();
            descendingExpectation1.add(new AbstractMap.SimpleImmutableEntry<>('a', 5));
            descendingExpectation1.add(new AbstractMap.SimpleImmutableEntry<>('c', 4));
            descendingExpectation1.add(new AbstractMap.SimpleImmutableEntry<>('b', 1));

            var descendingExpectation2 = new LinkedHashSet<Map.Entry<Character, Integer>>();
            descendingExpectation2.add(new AbstractMap.SimpleImmutableEntry<>('x', 3));
            descendingExpectation2.add(new AbstractMap.SimpleImmutableEntry<>('y', 3));
            descendingExpectation2.add(new AbstractMap.SimpleImmutableEntry<>('z', 3));

            var emptyExpectation = new LinkedHashSet<Map.Entry<Character, Integer>>();

            var ascendingExpectation1 = new LinkedHashSet<Map.Entry<Character, Integer>>();
            ascendingExpectation1.add(new AbstractMap.SimpleImmutableEntry<>('b', 1));
            ascendingExpectation1.add(new AbstractMap.SimpleImmutableEntry<>('c', 4));
            ascendingExpectation1.add(new AbstractMap.SimpleImmutableEntry<>('a', 5));

            var ascendingExpectation2 = new LinkedHashSet<Map.Entry<Character, Integer>>();
            ascendingExpectation2.add(new AbstractMap.SimpleImmutableEntry<>('x', 3));
            ascendingExpectation2.add(new AbstractMap.SimpleImmutableEntry<>('y', 3));
            ascendingExpectation2.add(new AbstractMap.SimpleImmutableEntry<>('z', 3));


            return Stream.of(
                    of(counterDescending, testcase1, descendingExpectation1),
                    of(counterDescending, testcase2, descendingExpectation2),
                    of(counterDescending, testcase3, emptyExpectation),
                    of(counterAscending, testcase1, ascendingExpectation1),
                    of(counterAscending, testcase2, ascendingExpectation2),
                    of(counterAscending, testcase3, emptyExpectation));
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FindCharactersFrequencyArgumentProvider.class)
    public void findCharactersFrequency(CharacterFrequencyCounter counter,
                                        char[] characters,
                                        Map<Character, Integer> expected) {

        if (characters == null || characters.length == 0) {
            assertThrowsExactly(IllegalArgumentException.class, () -> counter.findCharactersFrequency(characters));
            return;
        }

        var map = counter.findCharactersFrequency(characters);

        assertNotNull(map);
        assertEquals(map, expected);
    }

    @ParameterizedTest
    @ArgumentsSource(SortCharactersByFrequencyArgumentProvider.class)
    public void sortCharactersByFrequency(CharacterFrequencyCounter counter,
                                          Map<Character, Integer> map,
                                          LinkedHashSet<Map.Entry<Character, Integer>> expected) {
        var sorted = counter.sortCharactersByFrequency(map);

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
    }
}
