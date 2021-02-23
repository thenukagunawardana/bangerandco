package com.eirlss.bangerandco.DTO;

import lombok.Data;

@Data
public class ClientDTO
{

    private int id;

    private String status;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String driversLicense;

    private int age;

    public ClientDTO()
    {

    }

    public ClientDTO(int id, String firstName, String lastName, String email, String password, String driversLicense, int age)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.driversLicense = driversLicense;
        this.age = age;
    }
}
