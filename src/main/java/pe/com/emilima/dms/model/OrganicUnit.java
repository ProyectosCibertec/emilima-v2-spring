package pe.com.emilima.dms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "organic_unit")
@Getter
@Setter
@NoArgsConstructor
public class OrganicUnit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "name", length = 80)
    private String name;
}
