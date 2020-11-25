package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
