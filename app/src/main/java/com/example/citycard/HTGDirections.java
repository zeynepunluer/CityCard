package com.example.citycard;

import java.io.Serial;
import java.io.Serializable;

public class HTGDirections implements Serializable {
    private String title;
    private String description;

    public HTGDirections(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public HTGDirections() {
        // Boş yapıcı metodunda başka işlemler de ekleyebilirsiniz, ancak en azından bu kadarı gereklidir.
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}