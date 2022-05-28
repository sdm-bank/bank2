package ptl.cloud.bank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class Transaction extends Action {
    private Double value;
    @ManyToOne
    // not used at all
    @JsonIgnore
    private User owner;
}
