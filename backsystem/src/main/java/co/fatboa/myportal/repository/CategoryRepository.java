package co.fatboa.myportal.repository;

import co.fatboa.myportal.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query(value = "{parent.$id:?0}")
    Page<Category> query();
}
