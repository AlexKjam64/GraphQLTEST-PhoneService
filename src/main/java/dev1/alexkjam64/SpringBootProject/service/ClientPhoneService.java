package dev1.alexkjam64.SpringBootProject.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev1.alexkjam64.SpringBootProject.repository.ClientPhone;
import dev1.alexkjam64.SpringBootProject.repository.ClientPhoneRepository;

@Service
public class ClientPhoneService {
    private final ClientPhoneRepository clientRepository;

    public ClientPhoneService(ClientPhoneRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientPhone retrieve(int id) throws NoDataException{
        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        return clientRepository.getPhone(id);
    }

    public Map<Integer, ClientPhone> retrieveAllPhones(List<Integer> ids){
        return clientRepository.getBatchPhones(ids).stream().collect(Collectors.toMap(ClientPhone::id, phone->phone));
    }

    // Sanitizes data before adding to database
    public void create(ClientPhone request, int id) throws InvalidDataException{
        sanitizeData(request);
        
        // Assuming it past all the checks... call the repo to create
        clientRepository.addClient(request, id);
    }

    // Sanitizes data before updating database
    public void update(ClientPhone entity, int id) throws InvalidDataException, NoDataException{
        sanitizeData(entity);

        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        clientRepository.updateClient(entity, id);
    }

    public void delete(int id) throws NoDataException{
        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        clientRepository.deleteClient(id);
    }

    protected void sanitizeData(ClientPhone data) throws InvalidDataException{
        // If the numbers are null... blow up!
        if(data.countryCode() == null){
            throw new InvalidDataException("Country code is null or empty!");
        }
        if(data.areaCode() == null){
            throw new InvalidDataException("Area code is null or empty!");
        }
        if(data.localNumber() == null){
            throw new InvalidDataException("Local number is null or empty!");
        }
    }

    protected void checkDataExist(int id) throws NoDataException{
        if(clientRepository.getPhone(id) == null){
            throw new NoDataException("Data does not exist!");
        }
    }
}
