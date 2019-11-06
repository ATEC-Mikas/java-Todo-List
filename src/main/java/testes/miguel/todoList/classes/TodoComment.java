package testes.miguel.todoList.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="todo_comment")
//https://stackoverflow.com/questions/46092710/how-can-i-access-the-repository-from-the-entity-in-spring-boot todo?
public class TodoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false, name = "todo_id")
    private int todoid;
    @Column(nullable = false,insertable = false, updatable = false, name="created_at")
    private Date createdAt;
    @Column(nullable = false,insertable = false, updatable = false, name="updated_at")
    private Date updatedAt;


    protected TodoComment() {

    }

    public TodoComment(String comment, int todoId) {
        this.comment = comment;
        this.todoid = todoId;
    }

    public int getId() { return id; }
    public String getComment() { return comment; }
    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public int getTodoId() { return todoid; }

    public boolean setComment(String comment) {
        this.comment = comment;
        return true;
    }
    public boolean setTodoId(int id) {
        this.todoid = id;
        return true;
    }
}
