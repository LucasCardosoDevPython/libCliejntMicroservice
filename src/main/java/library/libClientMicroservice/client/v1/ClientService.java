package library.libClientMicroservice.client.v1;

import library.libClientMicroservice.client.Client;
import library.libClientMicroservice.client.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> listAllClient();
    ClientDTO getClientById(Integer id);
    Integer getClientId(String email);
    Client save(ClientDTO client);
    void delete(Integer id);
    void update(Integer id, ClientDTO client);
    List<ClientDTO> getClientByNameLike(String name);
}
