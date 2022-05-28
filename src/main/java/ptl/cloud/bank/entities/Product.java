package ptl.cloud.bank.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private Double balance;
    @ManyToOne
    private User owner;
    @OneToOne
    private InterestRate interestRate;
    @ManyToMany
    private List<Action> historyOfOperations;
}
