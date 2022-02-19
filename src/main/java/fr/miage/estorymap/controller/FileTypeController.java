package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.FileType;
import fr.miage.estorymap.repository.FileTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/file_types")
public class FileTypeController {

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @GetMapping
    public @ResponseBody Iterable<FileType> getAllFileTypes() {
        return fileTypeRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody FileType getFileTypeById(@PathVariable Long id) {
        return fileTypeRepository.existsById(id) ? fileTypeRepository.findById(id).get() : null;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewFileType(@RequestParam String label) {
        FileType fileType = new FileType(label);
        fileTypeRepository.save(fileType);
        return "FileType successfully added";
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteFileType(@PathVariable Long id) {
        if (fileTypeRepository.existsById(id)) {
            fileTypeRepository.deleteById(id);
            return String.format("FileType %s successfully deleted", id);
        }
        return String.format("FileType %s doesn't exist", id);
    }
}
