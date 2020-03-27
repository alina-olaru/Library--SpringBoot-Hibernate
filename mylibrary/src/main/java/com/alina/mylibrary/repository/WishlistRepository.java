package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.PersonalBook;
import com.alina.mylibrary.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional


public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}

