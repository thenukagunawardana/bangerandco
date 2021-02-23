package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>
{
    @Query("SELECT client from Client client where client.id = ?1")
    Client getById(int id);


}
