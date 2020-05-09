package com.alina.mylibrary.model.dashboard;

public class DashboardThreeItemsClass {

    public String titleOfCategory;
    public long numberBooksforCategory;
    public long numberBooksforAuthor;

    public DashboardThreeItemsClass(String titleOfCategory, long numberBooksforCategory, long numberBooksforAuthor) {
        this.titleOfCategory = titleOfCategory;
        this.numberBooksforCategory = numberBooksforCategory;
        this.numberBooksforAuthor = numberBooksforAuthor;
    }
}
