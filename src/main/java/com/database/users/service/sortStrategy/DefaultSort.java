package com.database.users.service.sortStrategy;

import org.springframework.data.domain.PageRequest;

public class DefaultSort implements SortStrategy {

    @Override
    public PageRequest createPageRequest(int page, int size, String direction, String... properties) {
        return PageRequest.of(page, size);
    }
}
