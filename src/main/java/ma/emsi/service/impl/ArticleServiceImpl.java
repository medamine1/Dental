package ma.emsi.service.impl;

import ma.emsi.service.api.ArticleService;
import ma.emsi.repository.api.ArticleRepository;
import ma.emsi.entities.Article;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @Override
    public Article getArticle(Long id) { return articleRepository.findById(id); }
    @Override
    public List<Article> getAllArticles() { return articleRepository.findAll(); }
    @Override
    public void createOrUpdateArticle(Article article) { articleRepository.save(article); }
    @Override
    public void deleteArticle(Long id) { articleRepository.delete(id); }
}
