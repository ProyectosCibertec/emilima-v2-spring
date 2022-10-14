package pe.com.emilima.dms.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Getter
@Setter
@NoArgsConstructor
public class File {
    @Id
    @Column(name = "id", length = 48)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private String id;
    @Column(name = "filename")
    @NotNull
    private String filename;
}
