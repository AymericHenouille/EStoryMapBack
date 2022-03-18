package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.FileType;
import fr.miage.estorymap.repository.FileTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileTypeController {

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @GetMapping("/file_types")
    public ResponseEntity<Iterable<FileType>> getAllFileTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(fileTypeRepository.findAll());
    }

    @GetMapping("/file_types/{id}")
    public ResponseEntity<FileType> getFileTypeById(@PathVariable Long id) {
        return fileTypeRepository.existsById(id)
                ? ResponseEntity.status(HttpStatus.OK).body(fileTypeRepository.findById(id).get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/file_types/add")
    public ResponseEntity<String> addNewFileType(@RequestParam String label) {
        FileType fileType = new FileType(label);
        fileTypeRepository.save(fileType);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("FileType successfully added %n"));
    }

    @DeleteMapping("/file_types/{id}")
    public ResponseEntity<String> deleteFileType(@PathVariable Long id) {
        if (fileTypeRepository.existsById(id)) {
            fileTypeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("FileType %s successfully deleted %n", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("FileType %s doesn't exist %n", id));
    }
}
