package pe.com.emilima.dms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "document_request")
@Getter
@Setter
@NoArgsConstructor
public class DocumentRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private RequestState requestState;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "organic_unit_id", nullable = false)
    private OrganicUnit organicUnit;
}
