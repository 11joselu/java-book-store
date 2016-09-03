package com.books.model;

/**
 * Created by joselu on 9/3/16.
 */
public class Category {
    private Long id;
    private String categoryDescription;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryDescription() {

        return this.categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String toString() {

        return "Category - ID: " + id + ", Category Description: " + categoryDescription;
    }
}
