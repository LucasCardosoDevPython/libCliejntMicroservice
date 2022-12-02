package library.libClientMicroservice.client.v1;

import library.libClientMicroservice.client.Client;
import library.libClientMicroservice.client.ClientDTO;
import library.libClientMicroservice.client.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ClientServiceImplementation implements ClientService{
    private ClientRepository clients;

    private static ClientDTO toClientDTO(Client client){
        return ClientDTO.builder()
                .id(client.getId())
                .email(client.getEmail())
                .name(client.getName())
                .birthDate(client.getBirthDate())
                .phone(client.getPhone())
                .sex(client.getSex())
                .build();
    }

    private Client fromClientDTO(ClientDTO dto){
        return Client.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .phone(dto.getPhone())
                .sex(dto.getSex())
                .build();
    }

    private Page<ClientDTO> fromPage(Page<Client> page){
        return page.map(ClientServiceImplementation::toClientDTO);
    }

    @Override
    @Transactional
    public Page<ClientDTO> listAllClient(Pageable pageable) {
        return this.fromPage(clients.findAll(pageable));
    }

    @Override
    @Transactional
    public ClientDTO getClientById(Integer id) {
        return this.toClientDTO(
                clients
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Não foi encontrado nenhum cliente com o id "+id+" na base de dados."
                        ))
        );
    }

    @Override
    @Transactional
    public Page<ClientDTO> getClientByNameLike(String name, Pageable pageable) {
        return this.fromPage(clients.findByNameContaining(name, pageable));
    }

    @Override
    @Transactional
    public boolean isPresent(Integer clientId) {
        return clients.findById(clientId).isPresent();
    }

    @Override
    @Transactional
    public Integer getClientId(String email) {
        return clients
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi encontrado nenhum cliente com o email "+email+" na base de dados."
                )).getId();
    }

    @Override
    @Transactional
    public Client save(ClientDTO client) {
        return clients.save(this.fromClientDTO(client));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        clients
                .findById(id)
                .map(client -> {
                    clients.delete(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi encontrado nenhum cliente com o id "+id+" na base de dados."
                ));
    }

    @Override
    @Transactional
    public void update(Integer id, ClientDTO client) {
        clients.
                findById(id).
                map(
                        existentClient -> {
                            Client c = this.fromClientDTO(client);
                            c.setId(existentClient.getId());
                            clients.save(c);
                            return existentClient;
                        }).orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi encontrado nenhum cliente com o id "+id+" na base de dados."
                ));
    }

}
