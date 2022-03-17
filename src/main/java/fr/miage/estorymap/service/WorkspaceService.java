package fr.miage.estorymap.service;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public Iterable<Workspace> findUserWorkspaces(Principal principal) {
        final Optional<User> user = userDetailsService.findUserByPrincipal(principal);
        final List<Workspace> workspaces = (List<Workspace>) this.workspaceRepository.findAll();
        return user.map(u -> workspaces.stream().filter(w -> userCanAccessToWorkspace(u, w)).collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }

    public Optional<Workspace> findWorkspaceByProject(Project project) {
        final List<Workspace> workspaces = (List<Workspace>) workspaceRepository.findAll();
        return workspaces.stream().filter(w -> w.getProjects().contains(project)).findFirst();
    }

    public boolean userCanAccessToWorkspace(User user, Workspace workspace) {
        return workspace.getUsers().contains(user) || workspace.getOwner().equals(user);
    }

    public Optional<Workspace> findWorkspaceById(long workspaceId) {
        return this.workspaceRepository.findById(workspaceId);
    }
}
