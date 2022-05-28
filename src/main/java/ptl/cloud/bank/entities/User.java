package ptl.cloud.bank.entities;

import lombok.Data;
import ptl.cloud.bank.security.Authorities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Authorities> authorities;
}
