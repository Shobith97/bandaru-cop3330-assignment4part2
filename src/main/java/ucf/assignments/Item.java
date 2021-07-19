package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Shobit Bandaru
 */
public class Item {
    private String id;
    private String description;
    private String dueDate;
    private boolean isComplete;

    public Item() {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return "id= " + id + " " +
                ", description= " + description + " " +
                ", dueDate= " + dueDate + " " +
                ", isComplete=" + isComplete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
