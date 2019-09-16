package com.database.users.service.sortStrategy;

import org.springframework.data.domain.PageRequest;

public interface SortStrategy {
    PageRequest createPageRequest(int page,int size, String direction, String...properties);
}
