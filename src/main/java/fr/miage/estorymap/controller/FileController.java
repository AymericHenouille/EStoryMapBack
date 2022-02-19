package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.File;
import fr.miage.estorymap.entity.FileType;
import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/files")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private FileTypeController fileTypeController;

    @GetMapping
    public @ResponseBody Iterable<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody File getFileById(@PathVariable Long id) {
        return fileRepository.existsById(id) ? fileRepository.findById(id).get() : null;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewFile(@RequestParam String path, @RequestParam Long projectId, @RequestParam Long fileTypeId) {
        Project project = projectController.getProjectById(projectId);
        if (project == null) {
            return String.format("Project %s doesn't exist", projectId);
        }

        FileType fileType = fileTypeController.getFileTypeById(fileTypeId);
        if (fileType == null) {
            return String.format("FileType %s doesn't exist", fileTypeId);
        }

        File file = new File(path, project, fileType);
        fileRepository.save(file);
        return "File successfully saved";
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteFile(@PathVariable Long id) {
        if (fileRepository.existsById(id)) {
            fileRepository.deleteById(id);
            return String.format("File %s successfully deleted", id);
        }
        return String.format("File %s doesn't exist", id);
    }
}
