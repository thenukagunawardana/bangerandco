package com.eirlss.bangerandco.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO
{

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;



}
