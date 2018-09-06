package co.fatboa.backsystem.repository;

import co.fatboa.backsystem.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @Auther: hl
 * @Date: 2018/9/6 09:57
 * @Description: 用户repository
 * @Modified By:
 * @Version 1.0
 */
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'name':?0,'password':?1}")
    User getUser(String name, String password);
}
