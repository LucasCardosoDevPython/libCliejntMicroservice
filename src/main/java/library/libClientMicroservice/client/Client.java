package library.libClientMicroservice.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="client")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Client {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ClientSeq")
    @SequenceGenerator(name = "ClientSeq", sequenceName = "client_id_generator", allocationSize = 1)
    @JsonIgnore
    private Integer id;
    @Column(length = 50, unique = true)
    private String email;
    @Column(length = 50)
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(length = 11)
    private String phone;
    @Column(length = 1)
    private String sex;

}
