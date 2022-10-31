package pe.com.emilima.dms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documental_serie")
@Getter
@Setter
@NoArgsConstructor
public class DocumentalSerie {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name", length = 200)
    private String name;
    @ManyToOne
    @JoinColumn(name = "hierarchical_dependency_id", nullable = false)
    private HierarchicalDependency hierarchicalDependency;
    @ManyToOne
    @JoinColumn(name = "organic_unit_id", nullable = false)
    private OrganicUnit organicUnit;
    @Column(name = "definition")
    private String definition;
    @Column(name = "service_frequency", length = 45)
    private String serviceFrequency;
    @Column(name = "normative_scope")
    private String normativeScope;
    @Column(name = "is_public")
    private Boolean isPublic;
    @Column(name = "phisical_features", length = 45)
    private String phisicalFeatures;
    @Column(name = "documental_serie_value", length = 1)
    private String documentalSerieValue;
    @Column(name = "years_in_management_archive")
    private String yearsInManagementArchive;
    @Column(name = "years_in_central_archive")
    private String yearsInCentralArchive;
    @Column(name = "elaboration_date")
    private Date elaborationDate;
}
