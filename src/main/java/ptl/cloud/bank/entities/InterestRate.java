package ptl.cloud.bank.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "interest_rates")
@Data
public class InterestRate {
    @Id
    @GeneratedValue
    private Long id;
    private Double percent;
    private String type;
    @ToString.Exclude
    @OneToOne
    private Product product;
    @ManyToOne
    @ToString.Exclude
    private User owner;
}
