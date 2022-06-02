package ptl.cloud.bank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue
    private Long id;
    private Integer cvc;
    private Long cardNumber;
    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "card")
    @ToString.Exclude
    @JsonIgnore
    private List<Account> accounts;
}
