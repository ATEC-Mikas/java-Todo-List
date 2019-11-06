package testes.miguel.todoList.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import testes.miguel.todoList.classes.Todo;
import testes.miguel.todoList.classes.TodoComment;
import testes.miguel.todoList.repositories.TodoCommentRepository;
import testes.miguel.todoList.repositories.TodoRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoCommentRepository todoCommentRepository;

    private final int NUMBER_OF_ITEMS = 5;
    private final int NUMBER_OF_ITEMS_COMMENTS = 5;

    @GetMapping("")
    public String index(Model model) {
//        List<Todo> result;
//        result = (List<Todo>) todoRepository.findAllByOrderByStatusAsc();
//        model.addAttribute("todos",result);
        Slice<Todo> todosDone;
        Slice<Todo> todosTodo;
        todosDone = todoRepository.findAllByStatus(true,null);
        todosTodo = todoRepository.findAllByStatus(false,null);
        model.addAttribute("todosDone",todosDone);
        model.addAttribute("todosTodo",todosTodo);


        return "todo/index";
    }

    @GetMapping("list")
    public String list(@RequestParam(value = "page",defaultValue = "1") int page,
                       @RequestParam(value = "checked", defaultValue = "true") boolean status,
                       Model model) {
        
        if(page<=0)
            page = 1;
        
        
        Slice<Todo> todos;
        Pageable pageable = PageRequest.of(page-1,NUMBER_OF_ITEMS, Sort.by("updatedAt").descending());
        todos = todoRepository.findAllByStatus(status,pageable);
        model.addAttribute("todos",todos);
        model.addAttribute("check",status);

        return "todo/list";
    }

    @GetMapping("/create")
    public String create() {
        return "todo/create";
    }

    @PostMapping("/create")
    public RedirectView save(@RequestBody MultiValueMap<String, String> formData) {
        String Name = formData.getFirst("name");
        String Description = formData.getFirst("description");

        if(Name==null || Name.trim().isEmpty() || Description==null || Description.trim().isEmpty())
            return new RedirectView("/todo/create");

        Todo result = new Todo(Name,Description);
        todoRepository.save(result);
        return new RedirectView("/todo");
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()) {
            model.addAttribute("todo",todo.get());
            return "todo/edit";
        }

        return "redirect:/todo";
    }

    @PostMapping("/edit/{id}")
    public RedirectView update(@PathVariable("id") int id, @RequestBody MultiValueMap<String, String> formData) {
        String Name = formData.getFirst("name");
        String Description = formData.getFirst("description");

        if(Name==null || Name.trim().isEmpty() || Description==null || Description.trim().isEmpty())
            return new RedirectView("/todo/create");

        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()) {
            Todo result = todo.get();
            result.setDescription(Description);
            result.setName(Name);
            todoRepository.save(result);
            return new RedirectView("/todo");
        }
        return new RedirectView("/todo");
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") int id) {
        todoRepository.deleteById(id);
        return new RedirectView("/todo");
    }

    @PostMapping("/check/{id}")
    public RedirectView check(@PathVariable("id") int id) {
        ChangeTodoStatus(id,true);
        return new RedirectView("/todo");
    }

    @PostMapping("/uncheck/{id}")
    public RedirectView uncheck(@PathVariable("id") int id) {
        ChangeTodoStatus(id,false);
        return new RedirectView("/todo");
    }

    //Comments

    @PostMapping("/{id}/comment")
    public RedirectView comment(@PathVariable("id") int id, @RequestBody MultiValueMap<String, String> formData) {
        String Comment = formData.getFirst("comment");
        if(Comment!=null && !Comment.trim().isEmpty() && todoRepository.existsById(id)) {

            TodoComment todoComment = new TodoComment(Comment,id);
            todoCommentRepository.save(todoComment);
        }
        return new RedirectView("/todo/"+id);
    }



    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       @RequestParam(value = "page",defaultValue = "1") int page,
                       Model model)
    {

        if(page<=0)
            page=1;

        Optional<Todo> result = todoRepository.findById(id);
        if(result.isPresent()) {
            Pageable pageable = PageRequest.of(page-1,NUMBER_OF_ITEMS_COMMENTS,Sort.by("updatedAt").descending());
            Slice<TodoComment> comments = todoCommentRepository.findAllByTodoid(id,pageable);
            model.addAttribute("comments",comments);
            model.addAttribute("todo",result.get());
            return "todo/show";
        }
        return "redirect:/todo";
    }

    private Boolean ChangeTodoStatus(int id, Boolean bool) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setStatus(bool);
            todoRepository.save(todo);
            return true;
        }
        return false;
    }
}
