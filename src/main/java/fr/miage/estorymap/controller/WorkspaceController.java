package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.WorkspaceRepository;
import fr.miage.estorymap.utils.exception.WorkspaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class WorkspaceController {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @GetMapping("/workspaces")
    public ResponseEntity<Iterable<Workspace>> getAllWorkspaces() {
        return ResponseEntity.ok(workspaceRepository.findAll());
    }

    @GetMapping("/workspaces/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable long id) throws WorkspaceNotFoundException {
        final Optional<Workspace> workspace = workspaceRepository.findById(id);
        return workspace.map(ResponseEntity::ok).orElseThrow(() -> new WorkspaceNotFoundException(id));
    }

    @PostMapping("/workspaces")
    public ResponseEntity<Workspace> createNewWorkspace(@RequestBody Workspace workspace) {
        return ResponseEntity.ok(workspaceRepository.save(workspace));
    }

    @PutMapping("/workspaces")
    public ResponseEntity<Workspace> updateWorkspace(@RequestBody Workspace workspace) throws WorkspaceNotFoundException {
        final long id = workspace.getId();
        final Optional<Workspace> presentWorkspace = workspaceRepository.findById(id);
        return presentWorkspace.map((foundWorkspace) -> ResponseEntity.ok(workspaceRepository.save(workspace)))
                .orElseThrow(() -> new WorkspaceNotFoundException(id));
    }

    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<Object> deleteWorkspace(@PathVariable Long id) throws WorkspaceNotFoundException {
        final Optional<Workspace> workspaceToDelete = workspaceRepository.findById(id);
        return workspaceToDelete.map((workspace) -> {
            workspaceRepository.delete(workspace);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new WorkspaceNotFoundException(id));
    }
}
