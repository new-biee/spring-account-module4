package com.codegym.model;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;


@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Please input first name")
    @Size(min = 5, max = 45)
    private String firstName;
    @NotEmpty(message = "Please input last name")
    @Size(min = 5, max = 45)
    private String lastName;

    @Min(18)
    private int age;

    @Pattern(regexp =  "^$|[0-9]*$")
    private String numberPhone;

    @Email(message = "Please input form email")
    private String email;

    public User() {
    }

    public User(Long id, @NotEmpty(message = "Please input first name") @Size(min = 5, max = 45) String firstName, @NotEmpty(message = "Please input last name") @Size(min = 5, max = 45) String lastName, @NotEmpty(message = "Please input age") @Min(18) int age, @Pattern(regexp = "^$|[0-9]*$") String numberPhone, @Email(message = "Please input form email") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.numberPhone = numberPhone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @Override
//    public boolean supports(Class<?> clazz) {
//        return User.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        User user = (User) target;
//        String firstName = user.getFirstName();
//        String lastName = user.getLastName();
//        String number = user.getNumberPhone();
//        String email = user.getEmail();
//        ValidationUtils.rejectIfEmpty(errors, "first", "number.ent");
//    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
