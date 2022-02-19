package fr.miage.estorymap.entity;

import fr.miage.estorymap.entity.id.SharedId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shared")
@IdClass(SharedId.class)
public class Shared implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shared_workspace_id", referencedColumnName = "idw")
    private Workspace workspace;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shared_user_id", referencedColumnName = "idu")
    private User user;

    protected Shared() {}

    public Shared(Workspace workspace, User user) {
        this.workspace = workspace;
        this.user = user;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public User getUser() {
        return user;
    }
}
