package ma.emsi.service.api;

import ma.emsi.entities.AgentMentuelle;
import java.util.List;

public interface AgentMentuelleService {
    AgentMentuelle getAgentMentuelle(Long id);
    List<AgentMentuelle> getAllAgentMentuelles();
    void createOrUpdateAgentMentuelle(AgentMentuelle agentMentuelle);
    void deleteAgentMentuelle(Long id);
}
