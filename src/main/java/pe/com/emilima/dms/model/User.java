package pe.com.emilima.dms.model;

import com.sun.istack.NotNull;
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
    @NotNull
    private String username;
    @Column(name = "password", length = 45)
    @NotNull
    private String password;
    @Column(name = "email", length = 100)
    @NotNull
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole userRole;
    @ManyToOne
    @JoinColumn(name = "photo_id", nullable = false)
    private File file;
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private UserPosition positionId;
}
