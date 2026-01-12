package ma.emsi.mvc.controllers;

import ma.emsi.service.api.AgentMentuelleService;
import ma.emsi.entities.AgentMentuelle;
import java.util.List;

public class AgentMentuelleController {
    private final AgentMentuelleService agentMentuelleService;
    public AgentMentuelleController(AgentMentuelleService agentMentuelleService) {
        this.agentMentuelleService = agentMentuelleService;
    }
    public AgentMentuelle getAgentMentuelle(Long id) { return agentMentuelleService.getAgentMentuelle(id); }
    public List<AgentMentuelle> getAllAgentMentuelles() { return agentMentuelleService.getAllAgentMentuelles(); }
    public void createOrUpdateAgentMentuelle(AgentMentuelle agentMentuelle) { agentMentuelleService.createOrUpdateAgentMentuelle(agentMentuelle); }
    public void deleteAgentMentuelle(Long id) { agentMentuelleService.deleteAgentMentuelle(id); }
}
