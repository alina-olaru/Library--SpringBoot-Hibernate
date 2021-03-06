package com.alina.mylibrary.model.requests;

import com.alina.mylibrary.model.db.Address;
import com.alina.mylibrary.model.db.BookUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressAdd {
    private BookUser user;
    private Address address;
}
