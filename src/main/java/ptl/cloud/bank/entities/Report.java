package ptl.cloud.bank.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reports")
@Data
public class Report {
    @Id
    @GeneratedValue
    private Long id;
    private Type type;
    private Double result;
    @ManyToOne
    private User owner;

    public enum Type {
        SUM_OF_BALANCES,
        AVERAGE_BALANCE,
        TOTAL_TRANSACTIONS;
    }
}
