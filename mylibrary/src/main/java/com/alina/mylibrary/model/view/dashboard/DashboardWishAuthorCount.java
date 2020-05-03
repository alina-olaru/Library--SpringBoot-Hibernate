package com.alina.mylibrary.model.view.dashboard;

import com.alina.mylibrary.model.db.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardWishAuthorCount {
    public Author author;
    public int count;
}
