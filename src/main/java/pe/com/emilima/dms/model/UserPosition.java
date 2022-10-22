package pe.com.emilima.dms.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user_position")
@Getter
@Setter
@NoArgsConstructor
public class UserPosition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private BigInteger id;
    @Column(name = "name", length = 200)
    private String name;
    @ManyToOne
    @JoinColumn(name = "organic_unit_id", nullable = false)
    private OrganicUnit organicUnit;
    @ManyToOne
    @JoinColumn(name = "hierarchical_dependency_id", nullable = false)
    private HierarchicalDependency hierarchicalDependency;
}
