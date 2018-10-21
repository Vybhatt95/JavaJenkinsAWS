package com.vishious.JavaJenkinsAWS.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class User {

    private String id;
    private String firstName;
    private String lastName;
}
