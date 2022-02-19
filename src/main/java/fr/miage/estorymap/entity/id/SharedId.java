package fr.miage.estorymap.entity.id;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;

import java.io.Serializable;
import java.util.Objects;

public class SharedId implements Serializable {

    private Workspace workspace;
    private User user;

    public SharedId() {}

    public SharedId(Workspace workspace, User user) {
        this.workspace = workspace;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedId sharedId = (SharedId) o;
        return Objects.equals(workspace, sharedId.workspace) && Objects.equals(user, sharedId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workspace, user);
    }
}
