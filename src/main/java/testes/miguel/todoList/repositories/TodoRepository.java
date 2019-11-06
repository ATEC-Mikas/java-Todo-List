package testes.miguel.todoList.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import testes.miguel.todoList.classes.Todo;


public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer> {

    Slice<Todo> findAllByStatus(Boolean status, Pageable pageable);
}
