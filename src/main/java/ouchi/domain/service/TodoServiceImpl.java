package ouchi.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ouchi.domain.model.Todo;
import ouchi.domain.repository.TodoRepository;
import ouchi.domain.repository.TodoSearchRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    @Inject
    private TodoRepository todoRepository;

    @Inject
    private TodoRepository todoCache;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    @Transactional
    public Todo create(Todo todo) {
        return todoCache.save(todo);
    }

    @Override
    public Todo findOne(Long todoId) {
        return todoCache.findOne(todoId);
    }

    @Override
    @Transactional
    public Todo update(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    @Transactional
    public void delete(Long todoId) {
        todoCache.delete(todoId);
    }

    @Override
    public List<Todo> search(String keyword) {
        return todoCache.searchTitleOrSummary(keyword);
    }
}
