package com.automationfraternity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
@Entity
public class Employee {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    Integer age;
    String email;
    String phone;
    String department;
    String salary;
    String designation;
}