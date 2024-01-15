package com.example.citycard;

import java.io.Serializable;

public class HTGDirections implements Serializable {
    private String title;
    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String description5;



    public HTGDirections(String title, String description1,String description2,String description3) {
        this.title = title;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.description4 = description4;
        this.description5 = description5;

    }
    public HTGDirections() {
        // Boş yapıcı metodunda başka işlemler de ekleyebilirsiniz, ancak en azından bu kadarı gereklidir.
    }

    public String getTitle() {
        return title;
    }

    public String getDescription1() {
        return description1;
    }
    public String getDescription2() {
        return description2;
    }
    public String getDescription3() {
        return description3;
    }
    public String getDescription4() {
        return description4;
    }
    public String getDescription5() {
        return description5;
    }

}