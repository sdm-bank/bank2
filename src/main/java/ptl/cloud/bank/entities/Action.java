package ptl.cloud.bank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actions")
@Data
public class Action {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(joinColumns = @JoinColumn(name = "action_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
