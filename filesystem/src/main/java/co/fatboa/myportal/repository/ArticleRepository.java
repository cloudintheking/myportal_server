package co.fatboa.myportal.repository;

import co.fatboa.myportal.domain.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
