package com.example.Firsttest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Garante auto incremento correto
    private Long id;

   //@Column(nullable = false)
    private String title;

    private boolean completed;
    private int tempoGasto;

    // Construtor vazio (necessário para desserialização JSON)
    public Todo() {}

    // Construtor com parâmetros
    public Todo(String title, boolean completed, int tempoGasto) {
        this.title = title;
        this.completed = completed;
        this.tempoGasto = tempoGasto;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(int tempoGasto) {
        this.tempoGasto = tempoGasto;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", tempoGasto=" + tempoGasto +
                '}';
    }
}