package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query("SELECT a FROM User a WHERE a.email=?1")
    User findByEmail(String email);

    @Query("SELECT a FROM User a WHERE a.email=?1")
    public List<User> findAll(String keyword);


}
