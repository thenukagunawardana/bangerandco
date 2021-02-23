package com.eirlss.bangerandco.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name =  "user", uniqueConstraints =
@UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private int tableID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;




    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

//    public User() {
//
//    }
//
//    public User(String firstName, String lastName, String email, String password, Collection<Role> roles,int tableID,int userID)
//    {
//        super();
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//        this.tableID=tableID;
//        this.userID=userID;
//    }
//    public int getId()
//    {
//        return id;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//    public String getFirstName() {
//        return firstName;
//    }
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//    public String getLastName() {
//        return lastName;
//    }
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password)
//    {
//        this.password = password;
//    }
    public Collection<Role> getRoles()
    {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }
//
//    public int getUserID() {
//        return userID;
//    }
//
//    public void setUserID(int userID) {
//        this.userID = userID;
//    }
}

