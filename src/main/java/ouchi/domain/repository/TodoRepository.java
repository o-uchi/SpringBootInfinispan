package ouchi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ouchi.domain.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
