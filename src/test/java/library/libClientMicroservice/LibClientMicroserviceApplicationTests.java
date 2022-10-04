package library.libClientMicroservice;

import library.libClientMicroservice.client.Client;
import library.libClientMicroservice.client.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class LibClientMicroserviceApplicationTests {

	@Autowired
	private ClientRepository repositorioCliente;

	@Test
	void clientTest() {
		Client cliente = Client.builder()
				.email("jao@gmail.com")
				.name("Jão")
				.phone("99999999999")
				.sex("M")
				.birthDate(LocalDate.parse("1999-05-15"))
				.build();

		Client clienteCadastrado = repositorioCliente.save(cliente);
		Optional<Client> clientePesquisado = repositorioCliente.findById(clienteCadastrado.getId());

		if(clientePesquisado.isEmpty()){
			fail("Cliente não encontrado quando deveria ter sido cadastrado");
		}else{
			assertEquals(clienteCadastrado.getEmail(),"jao@gmail.com");
			assertEquals(clienteCadastrado.getName(),clientePesquisado.get().getName());
			assertEquals(clienteCadastrado.getPhone(),clientePesquisado.get().getPhone());
			assertEquals(clienteCadastrado.getSex(),clientePesquisado.get().getSex());
			assertEquals(clienteCadastrado.getBirthDate(),clientePesquisado.get().getBirthDate());
		}

		cliente.setSex("F");

		Client clienteAtualizado = repositorioCliente.save(cliente);

		Optional<Client> clientePesquisado2 = repositorioCliente.findById(clienteCadastrado.getId());

		if(clientePesquisado2.isEmpty()){
			fail("Cliente não encontrado quando deveria ter sido cadastrado");
		}else{
			assertEquals(clienteAtualizado.getEmail(),clientePesquisado2.get().getEmail());
			assertEquals(clienteAtualizado.getName(),clientePesquisado2.get().getName());
			assertEquals(clienteAtualizado.getPhone(),clientePesquisado2.get().getPhone());
			assertEquals("F",clientePesquisado2.get().getSex());
			assertEquals(clienteAtualizado.getBirthDate(),clientePesquisado2.get().getBirthDate());
		}

		repositorioCliente.delete(repositorioCliente.findById(clienteAtualizado.getId()).get());

		if(!repositorioCliente.findById(clienteAtualizado.getId()).isEmpty()){
			fail("O cliente deveria ter sido deletado.");
		}

	}

}
