package testes.miguel.todoList.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import testes.miguel.todoList.classes.TodoComment;

public interface TodoCommentRepository extends PagingAndSortingRepository<TodoComment, Integer> {

    Slice<TodoComment> findAllByTodoid(int todoId,Pageable pageable);
}
