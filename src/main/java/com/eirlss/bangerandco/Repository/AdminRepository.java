package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository <Admin, Integer>
{

}
