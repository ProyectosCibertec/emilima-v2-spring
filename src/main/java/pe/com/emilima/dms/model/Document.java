package pe.com.emilima.dms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "document")
@Getter
@Setter
@NoArgsConstructor
public class Document {
    @Id
    @Column(name = "serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger serialNumber;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "upload_date")
    private Date uploadDate;
    @Column(name = "creation_date")
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;
    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;
    @ManyToOne
    @JoinColumn(name = "document_serie_id", nullable = false)
    private DocumentalSerie documentSerie;
    @ManyToOne
    @JoinColumn(name = "document_request_id")
    private DocumentRequest documentRequest;
}
