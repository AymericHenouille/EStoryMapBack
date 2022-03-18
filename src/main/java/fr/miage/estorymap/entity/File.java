package fr.miage.estorymap.entity;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idf;

    @Column(nullable = false)
    private String path;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_project_id", referencedColumnName = "idp", nullable = false)
    private Project project;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_file_type_id", referencedColumnName = "idft", nullable = false)
    private FileType fileType;

    protected File() {}

    public File(String path, Project project, FileType fileType) {
        this.path = path;
        this.project = project;
        this.fileType = fileType;
    }

    public Long getIdf() {
        return idf;
    }

    public String getPath() {
        return path;
    }

    public Project getProject() {
        return project;
    }

    public FileType getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return "File{" +
                "idf=" + idf +
                ", path='" + path + '\'' +
                ", project=" + project +
                ", fileType=" + fileType +
                '}';
    }
}
