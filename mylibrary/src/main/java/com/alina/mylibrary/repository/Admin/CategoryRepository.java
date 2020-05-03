package com.alina.mylibrary.repository.Admin;


import com.alina.mylibrary.model.db.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findBycategoryTitle (String categoryTitle);

}
