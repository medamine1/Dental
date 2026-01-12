package ma.emsi.mvc.controllers;

import ma.emsi.service.api.ArticleService;
import ma.emsi.entities.Article;
import java.util.List;

public class ArticleController {
    private final ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    public Article getArticle(Long id) { return articleService.getArticle(id); }
    public List<Article> getAllArticles() { return articleService.getAllArticles(); }
    public void createOrUpdateArticle(Article article) { articleService.createOrUpdateArticle(article); }
    public void deleteArticle(Long id) { articleService.deleteArticle(id); }
}
