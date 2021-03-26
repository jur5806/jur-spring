package com.jurspring.jt.dto;

import com.jurspring.jt.dto.base.OutputConverter;
import com.jurspring.jt.home.AdminRole;
import com.jurspring.jt.home.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UserDTO implements OutputConverter<UserDTO, User> {

    private int id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private boolean enabled;

    private List<AdminRole> roles;

}