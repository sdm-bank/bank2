package ptl.cloud.bank.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class Loan extends Product {
    // annual percentage yield
    private Double apy;
}
