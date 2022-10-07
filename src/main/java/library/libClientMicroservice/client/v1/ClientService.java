package library.libClientMicroservice.client.v1;

import library.libClientMicroservice.client.Client;
import library.libClientMicroservice.client.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Page<ClientDTO> listAllClient(Pageable pageable);
    ClientDTO getClientById(Integer id);
    Integer getClientId(String email);
    Client save(ClientDTO client);
    void delete(Integer id);
    void update(Integer id, ClientDTO client);
    Page<ClientDTO> getClientByNameLike(String name, Pageable pageable);
    boolean isPresent(Integer clientId);
}
