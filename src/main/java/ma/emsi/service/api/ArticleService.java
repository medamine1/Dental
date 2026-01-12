package ma.emsi.service.api;

import ma.emsi.entities.Article;
import java.util.List;

public interface ArticleService {
    Article getArticle(Long id);
    List<Article> getAllArticles();
    void createOrUpdateArticle(Article article);
    void deleteArticle(Long id);
}
