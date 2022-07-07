package service;

import model.Client;
import repository.ClientRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Client client) {
        try {
            this.clientRepository.add(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client client) {
        try {
            this.clientRepository.update(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) {
        try {
            this.clientRepository.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClient(int id) {
        try {
            Optional<Client> client = this.clientRepository.getByID(id);
            return client.orElse(new Client());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Client();
        }
    }

    public Client getClient(String name) {
        try {
            Optional<Client> client = this.clientRepository.getByName(name);
            return client.orElse(new Client());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Client();
        }
    }

    public List<Optional<Client>> getAllClients() {
        try {
            return this.clientRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
