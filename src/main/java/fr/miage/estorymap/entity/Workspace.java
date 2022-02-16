package fr.miage.estorymap.entity;

import javax.persistence.*;

@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idw;

    @Column(nullable = false)
    private String label;

    @Column(length = 7)
    private String color;

    @Column(nullable = true)
    private char emoji;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workspace_user_id", referencedColumnName = "idu", nullable = false)
    private User user;

    protected Workspace() {}

    public Workspace(String label, String color, char emoji, User user) {
        this.label = label;
        this.color = color;
        this.emoji = emoji;
        this.user = user;
    }

    public Long getIdw() {
        return idw;
    }

    public String getLabel() {
        return label;
    }

    public String getColor() {
        return color;
    }

    public char getEmoji() {
        return emoji;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "idw=" + idw +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                ", emoji=" + emoji +
                ", user=" + user +
                '}';
    }
}
