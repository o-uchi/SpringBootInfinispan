package ouchi.web;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ouchi.domain.model.Todo;
import ouchi.domain.service.TodoService;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    @Inject
    private TodoService todoService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> getTodos() {
        return todoService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Todo> postTodos(@RequestBody Todo todo) {
        Todo created = todoService.create(todo);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{todoId}", method = RequestMethod.GET)
    public Todo getTodo(@PathVariable Long todoId) {
        return todoService.findOne(todoId);
    }

    @RequestMapping(value = "{todoId}", method = RequestMethod.PUT)
    public Todo putTodo(@PathVariable Long todoId, @RequestBody Todo todo) {
        Todo target = todoService.findOne(todoId);
        BeanUtils.copyProperties(todo, target, "createdBy");
        return todoService.update(target);
    }

    @RequestMapping(value = "{todoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> putTodo(@PathVariable Long todoId) {
        todoService.delete(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "search/{keyword}", method = RequestMethod.GET)
    public List<Todo> searchTodos(@PathVariable String keyword) {
        return todoService.search(keyword);
    }
}
