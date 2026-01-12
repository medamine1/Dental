package ma.emsi.repository.impl;

import ma.emsi.repository.api.AgentMentuelleRepository;
import ma.emsi.entities.AgentMentuelle;
import java.util.*;

public class AgentMentuelleRepositoryImpl implements AgentMentuelleRepository {
    private final Map<Long, AgentMentuelle> db = new HashMap<>();
    @Override
    public AgentMentuelle findById(Long id) { return db.get(id); }
    @Override
    public List<AgentMentuelle> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(AgentMentuelle agentMentuelle) { db.put(agentMentuelle.getId(), agentMentuelle); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
