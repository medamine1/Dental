package ma.emsi.service.impl;

import ma.emsi.service.api.AgentMentuelleService;
import ma.emsi.repository.api.AgentMentuelleRepository;
import ma.emsi.entities.AgentMentuelle;
import java.util.List;

public class AgentMentuelleServiceImpl implements AgentMentuelleService {
    private final AgentMentuelleRepository agentMentuelleRepository;
    public AgentMentuelleServiceImpl(AgentMentuelleRepository agentMentuelleRepository) {
        this.agentMentuelleRepository = agentMentuelleRepository;
    }
    @Override
    public AgentMentuelle getAgentMentuelle(Long id) { return agentMentuelleRepository.findById(id); }
    @Override
    public List<AgentMentuelle> getAllAgentMentuelles() { return agentMentuelleRepository.findAll(); }
    @Override
    public void createOrUpdateAgentMentuelle(AgentMentuelle agentMentuelle) { agentMentuelleRepository.save(agentMentuelle); }
    @Override
    public void deleteAgentMentuelle(Long id) { agentMentuelleRepository.delete(id); }
}
