package com.example.applicationjave.Model;




public class notes {

    String id;
    String text;
    private  notes(){}
    public notes(String id, String text) {
        this.id = id;
        this.text = text;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

}
