package com.alina.mylibrary.model.auth;

import com.alina.mylibrary.model.BookUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse  implements Serializable {
    private final String jwttoken;
    private final BookUser bookUser;
}
