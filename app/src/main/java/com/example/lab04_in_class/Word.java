package com.example.lab04_in_class;

public class Word {
    private int id;
    private String word;
    private String definition;

    public Word(int id, String word, String definition) {
        this.id = id;
        this.word = word;
        this.definition = definition;
    }

    public int getId() { return id; }
    public String getWord() { return word; }
    public String getDefinition() { return definition; }

    @Override
    public String toString() { return word; }
}