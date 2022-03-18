package fr.miage.estorymap.entity;

import javax.persistence.*;

@Entity
@Table(name = "file_types")
public class FileType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idft;

    @Column(nullable = false)
    private String label;

    protected FileType() {}

    public FileType(String label) {
        this.label = label;
    }

    public Long getIdft() {
        return idft;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "FileType{" +
                "idft=" + idft +
                ", label='" + label + '\'' +
                '}';
    }
}
