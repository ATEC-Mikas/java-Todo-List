package testes.miguel.todoList.classes;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, insertable = false)
    private Boolean status;
    @Column(nullable = false, insertable = false, updatable = false,name="created_at")
    private Date createdAt;
    @Column(nullable = false, insertable = false, updatable = false,name="updated_at")
    private Date updatedAt;

    protected Todo() {

    }

    public Todo(String Name, String Description) {
        this.name = Name;
        this.description = Description;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public Boolean getStatus() {return status;}
    public Date getCreatedAt() {return createdAt;}
    public Date getUpdatedAt() {return updatedAt;}

    public Boolean setName(String Name) {this.name = Name; return true;}
    public Boolean setDescription(String Description) {this.description = Description; return true;}
    public Boolean setStatus(Boolean status) { this.status = status; return true;}
//    @PreUpdate
//    public void setLastUpdate() {this.updated_at = new Date();}
}
