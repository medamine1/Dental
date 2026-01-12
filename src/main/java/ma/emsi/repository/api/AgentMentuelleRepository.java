package ma.emsi.repository.api;

import ma.emsi.entities.AgentMentuelle;
import java.util.List;

public interface AgentMentuelleRepository {
    AgentMentuelle findById(Long id);
    List<AgentMentuelle> findAll();
    void save(AgentMentuelle agentMentuelle);
    void delete(Long id);
}
