package com.eirlss.bangerandco.Service;


import com.eirlss.bangerandco.Model.Client;
import com.eirlss.bangerandco.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    public Client getClientByID(int id)
    {

        return clientRepository.getById(id);
    }
}
