package com.blankspace.orm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Person {

    private int id;

    private String name;

    private String phone;

    private String email;

    private String company;

    private String address;

}
