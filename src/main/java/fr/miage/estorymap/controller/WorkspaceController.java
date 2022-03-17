package fr.miage.estorymap.controller;

import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.WorkspaceRepository;
import fr.miage.estorymap.service.CustomUserDetailsService;
import fr.miage.estorymap.service.WorkspaceService;
import fr.miage.estorymap.utils.exception.UserNotFoundException;
import fr.miage.estorymap.utils.exception.WorkspaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class WorkspaceController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @GetMapping("/workspaces")
    public ResponseEntity<Iterable<Workspace>> getAllWorkspaces(Principal principal) {
        final Iterable<Workspace> workspaces = workspaceService.findUserWorkspaces(principal);
        return ResponseEntity.ok(workspaces);
    }

    @GetMapping("/workspaces/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable long id, Principal principal) throws WorkspaceNotFoundException {
        final List<Workspace> workspaces = (List<Workspace>) workspaceService.findUserWorkspaces(principal);
        return workspaces.stream().filter(w -> w.getId() == id).map(ResponseEntity::ok).findFirst()
                .orElseThrow(() -> new WorkspaceNotFoundException(id));
    }

    @PostMapping("/workspaces")
    public ResponseEntity<Workspace> createNewWorkspace(@RequestBody Workspace workspace, Principal principal) throws UserNotFoundException {
        final Optional<User> user = userDetailsService.findUserByPrincipal(principal);
        user.ifPresent(workspace::setOwner);
        return user.map(u -> ResponseEntity.ok(workspaceRepository.save(workspace)))
                .orElseThrow(UserNotFoundException::new);
    }

    @PutMapping("/workspaces")
    public ResponseEntity<Workspace> updateWorkspace(@RequestBody Workspace workspace, Principal principal) throws WorkspaceNotFoundException {
        final long id = workspace.getId();
        final List<Workspace> workspaces = (List<Workspace>) workspaceService.findUserWorkspaces(principal);
        final boolean find = workspaces.stream().anyMatch(w -> w.getId() == id);
        if (find) {
            return ResponseEntity.ok(workspaceRepository.save(workspace));
        }
        throw new WorkspaceNotFoundException(id);
    }

    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<Object> deleteWorkspace(@PathVariable long id, Principal principal) throws WorkspaceNotFoundException {
        final List<Workspace> workspaces = (List<Workspace>) workspaceService.findUserWorkspaces(principal);
        final boolean find = workspaces.stream().anyMatch(w -> w.getId() == id);
        if (find) {
            workspaceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new WorkspaceNotFoundException(id);
    }
}
