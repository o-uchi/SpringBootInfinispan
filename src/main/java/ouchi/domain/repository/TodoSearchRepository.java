package ouchi.domain.repository;

import ouchi.domain.model.Todo;

import java.util.List;

public interface TodoSearchRepository {
    List<Todo> searchTitleOrSummary(String keyword);
}
