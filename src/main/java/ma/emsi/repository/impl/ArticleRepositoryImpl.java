package ma.emsi.repository.impl;

import ma.emsi.repository.api.ArticleRepository;
import ma.emsi.entities.Article;
import java.util.*;

public class ArticleRepositoryImpl implements ArticleRepository {
    private final Map<Long, Article> db = new HashMap<>();
    @Override
    public Article findById(Long id) { return db.get(id); }
    @Override
    public List<Article> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Article article) { db.put(article.getId(), article); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
