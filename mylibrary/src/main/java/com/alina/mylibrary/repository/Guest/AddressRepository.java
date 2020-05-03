package com.alina.mylibrary.repository.Guest;

import com.alina.mylibrary.model.db.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
