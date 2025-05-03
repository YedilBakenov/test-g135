package kz.test.g135test.entity;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Student {

    private int id;
    private String fullName;
    private double gpa;
    private String city;
    private int age;

}
