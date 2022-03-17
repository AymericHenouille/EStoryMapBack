package fr.miage.estorymap.service;

import fr.miage.estorymap.entity.Project;
import fr.miage.estorymap.entity.User;
import fr.miage.estorymap.entity.Workspace;
import fr.miage.estorymap.repository.ProjectRepository;
import fr.miage.estorymap.repository.WorkspaceRepository;
import fr.miage.estorymap.utils.exception.ProjectNotFoundException;
import fr.miage.estorymap.utils.exception.WorkspaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private CustomUserDetailsService userService;

    public Iterable<Project> findUserProjects(Principal principal) {
        final List<Workspace> workspaces = (List<Workspace>) workspaceService.findUserWorkspaces(principal);
        return workspaces.stream().flatMap(workspace -> workspace.getProjects().stream())
                .collect(Collectors.toList());
    }

    public Workspace findProjectWorkspace(Project project) throws WorkspaceNotFoundException {
        return workspaceService.findWorkspaceByProject(project)
                .orElseThrow(() -> new WorkspaceNotFoundException(project.getId()));
    }

    public Project findUserProjects(Principal principal, long id) throws ProjectNotFoundException {
        final List<Project> projects = (List<Project>) findUserProjects(principal);
        return projects.stream().filter(project -> project.getId() == id).findFirst().orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project createProject(Principal principal, long workspaceId, Project project) throws WorkspaceNotFoundException {
        final Optional<Workspace> workspace = workspaceService.findWorkspaceById(workspaceId);
        final Optional<User> user = userService.findUserByPrincipal(principal);
        if (user.isPresent() && workspace.isPresent()) {
            if (workspaceService.userCanAccessToWorkspace(user.get(), workspace.get())) {
                project = projectRepository.save(project);
                workspace.get().getProjects().add(project);
                workspaceRepository.save(workspace.get());
                return project;
            }
        }
        throw new WorkspaceNotFoundException(workspaceId);
    }

    public Project updateProject(Principal principal, long workspaceId, Project project) throws ProjectNotFoundException, WorkspaceNotFoundException {
        final Optional<Workspace> workspace = workspaceService.findWorkspaceById(workspaceId);
        final Optional<User> user = userService.findUserByPrincipal(principal);
        if (user.isPresent() && workspace.isPresent()) {
            if (workspaceService.userCanAccessToWorkspace(user.get(), workspace.get())) {
                if (workspace.get().getProjects().stream().anyMatch(p -> p.getId() == project.getId())) {
                    return projectRepository.save(project);
                }
                throw new ProjectNotFoundException(project.getId());
            }
        }
        throw new WorkspaceNotFoundException(workspaceId);
    }

    public Project findProjectByIdForUser(Principal principal, long id) throws ProjectNotFoundException {
        final Optional<Project> project = projectRepository.findById(id);
        final Optional<User> user = userService.findUserByPrincipal(principal);
        if (project.isPresent() && user.isPresent()) {
            final Optional<Workspace> workspace = workspaceService.findWorkspaceByProject(project.get());
            if (workspace.isPresent() && workspaceService.userCanAccessToWorkspace(user.get(), workspace.get())) {
                return project.get();
            }
        }
        throw new ProjectNotFoundException(id);
    }
}
