package library.libClientMicroservice.client;

import library.libClientMicroservice.client.v1.ClientServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
@AllArgsConstructor
public class ClientController {

    private ClientServiceImplementation service;

    @GetMapping
    public List<ClientDTO> listAllClient(){
        return service.listAllClient();
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
    public List<ClientDTO> getClientByNameLike(@PathVariable("name") String name){
        return service.getClientByNameLike(name);
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
