package com.example.dataexportservice;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Student{

    @Id
    private Long id;
    private String name;
    private String section;

}