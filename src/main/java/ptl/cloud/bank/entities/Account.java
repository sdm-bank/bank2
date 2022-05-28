package ptl.cloud.bank.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class Account extends Product {
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private String currency;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Card card;
}
