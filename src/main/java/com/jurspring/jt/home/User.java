package com.jurspring.jt.home;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Username.
     */
//    @NotEmpty(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * Password.
     */
    private String password;

    /**
     * Salt for encoding.
     */
    private String salt;

    /**
     * Real name.
     */
    private String name;

    /**
     * Phone number.
     */
    private String phone;

    /**
     * Email address.
     *
     * A Email address can be null,but should be correct if exists.
     */
//    @Email(message = "请输入正确的邮箱")
    private String email;

    /**
     * User status.
     */
    private boolean enabled;

    /**
     * User setSex
     */
    @Column(name = "sex")
    private int Sex = 0;

    /**
     * User setSex
     */
    @Column(name = "sum_points")
    private int sumPoints = 0;

    @CreatedDate
    @Column(name = "birth",nullable = true)
    private Date birthDate;
    /**
     * Transient property for storing role owned by current user.
     */
    @Transient
    private List<AdminRole> roles;

}
