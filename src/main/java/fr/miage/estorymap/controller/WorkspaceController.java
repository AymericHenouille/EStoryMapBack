package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.UserRepository;
import fr.miage.estorymap.repository.WorkspaceRepository;
import fr.miage.estorymap.service.CustomUserDetailsService;
import fr.miage.estorymap.utils.exception.WorkspaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class WorkspaceController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @GetMapping("/workspaces")
    public ResponseEntity<Iterable<Workspace>> getAllWorkspaces(Principal principal) {
        final Optional<User> user = userDetailsService.findUserByPrincipal(principal);
        return user.map((foundUser) -> {
            final List<Workspace> workspaces = StreamSupport.stream(workspaceRepository.findAllByUser(foundUser.getId()).spliterator(), false).collect(Collectors.toList());
            workspaces.addAll(StreamSupport.stream(workspaceRepository.findAllSharedWithId(foundUser.getId()).spliterator(), false).collect(Collectors.toList()));
            return ResponseEntity.ok((Iterable<Workspace>) workspaces);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/workspaces/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable long id) throws WorkspaceNotFoundException {
        final Optional<Workspace> workspace = workspaceRepository.findById(id);
        return workspace.map(ResponseEntity::ok).orElseThrow(() -> new WorkspaceNotFoundException(id));
    }

    @PostMapping("/workspaces")
    public ResponseEntity<Workspace> createNewWorkspace(@RequestBody Workspace workspace, Principal principal) {
        final Optional<User> user = userDetailsService.findUserByPrincipal(principal);
        user.ifPresent(workspace::setOwner);
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
