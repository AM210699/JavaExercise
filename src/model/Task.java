package model;

import java.io.Serializable;
import java.time.LocalDate;


public class Task {

    private String taskName;
    private Boolean isDone;
    private static LocalDate dateRealization;


    {
        this.dateRealization = LocalDate.now();
    }

    public Task(String name) {
        this.taskName = name;

    }

    public String getTaskName() {
        return taskName;
    }

    public Boolean getIsDone() {
        return isDone;
    }
    public void setIsDone(Boolean isCreated) {
        this.isDone = isCreated;
    }
    public LocalDate getDateRealization() {
        return dateRealization;
    }
    public void setDateRealization(LocalDate dateRealization) {
        this.dateRealization = dateRealization;
    }


}

