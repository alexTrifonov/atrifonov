package ru.job4j.todolist;

import java.sql.Timestamp;

/**
 * Class for task entity.
 * @author atrifonov.
 * @version 1.
 * @since 27.02.2012.
 */
public class Task {
    private int id;
    private String description;
    private Timestamp created;
    private boolean done;

    public Task() {

    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}