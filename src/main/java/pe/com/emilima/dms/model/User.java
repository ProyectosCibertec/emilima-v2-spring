package pe.com.emilima.dms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "username", length = 45)
    private String username;
    @Column(name = "password", length = 45)
    private String password;
    @Column(name = "email", length = 100)
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole userRole;
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private File file;
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private UserPosition userPosition;
}
