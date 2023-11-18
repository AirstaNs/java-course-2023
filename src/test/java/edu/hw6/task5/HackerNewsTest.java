package edu.hw6.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HackerNewsTest {
    private static final HackerNews hackerNews = new HackerNews();

    @Test
    @DisplayName("Должен возвращать список верхних историй HackerNews")
    public void hackerNewsTopStories_ShouldReturnTopStories() {

        // When
        long[] topStories = hackerNews.hackerNewsTopStories();

        // Then
        assertThat(topStories).isNotEmpty();
    }

    @Test
    @DisplayName("Должен возвращать заголовок новости по валидному ID")
    public void news_ShouldReturnNewsTitleForValidId() {
        // When
        long[] topStories = hackerNews.hackerNewsTopStories();
        String title = hackerNews.getNewsTitle(topStories[0]);

        // Then
        assertThat(title).isNotEmpty();
    }

    @Test
    @DisplayName("Должно бросать исключение для новости с неверным ID")
    public void news_ShouldThrowExceptionForInvalidId() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> hackerNews.getNewsTitle(-1));
    }
}
