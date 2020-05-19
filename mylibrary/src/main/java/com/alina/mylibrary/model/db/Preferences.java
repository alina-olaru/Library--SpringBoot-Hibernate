package com.alina.mylibrary.model.db;

import java.util.Objects;

public class Preferences {
    public String first_name;

    public String getFirst_name() {
        return first_name;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "first_name='" + first_name + '\'' +
                ", last_title='" + last_name + '\'' +
                '}';
    }

    @Override


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preferences that = (Preferences) o;
        return Objects.equals(first_name, that.first_name) &&
                Objects.equals(last_name, that.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name);
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getlast_name() {
        return last_name;
    }

    public void setLast_title(String last_name) {
        this.last_name = last_name;
    }

    public String last_name;

    public Preferences(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
