package com.example.applicationjave.Model;




public class notes {

    String id;
    String header;
    String text;
    private  notes(){}
    public notes(String id,  String  header, String text  ) {
        this.id = id;
        this.header = header  ;
        this.text = text;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

}
