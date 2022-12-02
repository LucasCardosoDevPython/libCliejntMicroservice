package library.libClientMicroservice.client;

import library.libClientMicroservice.client.v1.ClientServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ClientController {

    private ClientServiceImplementation service;

    @GetMapping("/verify/{id}")
    public boolean isPresent(@PathVariable("id") Integer clientId){
        return service.isPresent(clientId);
    }

    @GetMapping
    public Page<ClientDTO> listAllClient(Pageable pageable){
        return service.listAllClient(pageable);
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable("id") Integer id){
        return service.getClientById(id);
    }

    @GetMapping("/email/{email}")
    public Integer getClientId(@PathVariable("email") String email){
        return service.getClientId(email);
    }

    @GetMapping("/name/{name}")
    public Page<ClientDTO> getClientByNameLike(@PathVariable("name") String name, Pageable pageable){
        return service.getClientByNameLike(name, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody ClientDTO client){
        return service.save(client).getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody ClientDTO client){
        service.update(id, client);
    }

}
