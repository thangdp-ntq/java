package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    public  Integer id;
    public String name;
    public String author;
    public String version;
    public Date publishing_year;
    public  Integer number_pages;
    public  Boolean is_available;
}
