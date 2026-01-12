package ma.emsi.repository.api;

import ma.emsi.entities.Article;
import java.util.List;

public interface ArticleRepository {
    Article findById(Long id);
    List<Article> findAll();
    void save(Article article);
    void delete(Long id);
}
