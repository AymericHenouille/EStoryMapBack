package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.File;
import fr.miage.estorymap.entity.FileType;
import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.repository.FileRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("/files")
@ResponseBody
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private FileTypeController fileTypeController;

    @GetMapping
    public ResponseEntity<Iterable<File>> getAllFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(fileRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        return fileRepository.existsById(id)
                ? ResponseEntity.status(HttpStatus.OK).body(fileRepository.findById(id).get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewFile(@RequestParam String path, @RequestParam Long projectId, @RequestParam Long fileTypeId) {
        Project project = projectController.getProjectById(projectId).getBody();
        if (project == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Project %s doesn't exist", projectId));
        }

        FileType fileType = fileTypeController.getFileTypeById(fileTypeId).getBody();
        if (fileType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("FileType %s doesn't exist", fileTypeId));
        }

        File file = new File(path, project, fileType);
        fileRepository.save(file);
        return ResponseEntity.status(HttpStatus.OK).body("File successfully saved");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        if (fileRepository.existsById(id)) {
            fileRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("File %s successfully deleted", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("File %s doesn't exist", id));
    }
}
