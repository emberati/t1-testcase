package com.emb.test.t1.data.dto;

/**
 * Структура описывающая модель данных POST запроса /freq.
 * Хранит исходную строку и порядок сортировки символов по частоте:
 * по убыванию или возрастанию (descending, ascending).
 * @param string
 * @param order
 */
public record CharactersSortInfo(String string, String order) {
}
