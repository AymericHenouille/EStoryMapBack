package fr.miage.estorymap.model;

public class Shared {

    private String userId;
    public String getUserId() {
        return userId;
    }

    private Long workspaceId;
    public Long getWorkspaceId() {
        return workspaceId;
    }

    public Shared() {
    }

    public Shared(String userId, Long workspaceId) {
        this.userId = userId;
        this.workspaceId = workspaceId;
    }
}
