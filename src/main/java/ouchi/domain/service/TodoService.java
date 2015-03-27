package ouchi.domain.service;

import ouchi.domain.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();
    Todo create(Todo todo);
    Todo findOne(Long todoId);
    Todo update(Todo todo);
    void delete(Long todoId);
    List<Todo> search(String keyword);
}
