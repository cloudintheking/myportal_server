package co.fatboa.filesystem.repository;

import co.fatboa.filesystem.domain.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
