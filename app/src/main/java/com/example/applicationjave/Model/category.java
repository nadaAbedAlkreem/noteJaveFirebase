package com.example.applicationjave.Model;




public class category {

    String id;
     String name;
    private category(){}
    public category(String id, String  name  ) {
        this.id = id;
        this.name = name  ;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
