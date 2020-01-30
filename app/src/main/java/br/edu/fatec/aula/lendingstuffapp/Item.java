package br.edu.fatec.aula.lendingstuffapp;

import java.io.Serializable;

public class Item implements Serializable {

    private long id;
    private String description;
    private String name;
    private String date;

    public Item(long id, String description, String name, String date) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.date = date;
    }

    public Item(String description, String name, String date) {
        this(0, description, name, date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
