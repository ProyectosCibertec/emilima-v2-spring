package pe.com.emilima.dms.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "hierarchical_dependency")
@Getter
@Setter
@NoArgsConstructor
public class HierarchicalDependency {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private BigInteger id;
    @Column(name = "name", length = 200)
    @NotNull
    private String name;
}
