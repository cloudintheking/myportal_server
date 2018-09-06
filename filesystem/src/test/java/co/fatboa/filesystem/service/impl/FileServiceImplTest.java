package co.fatboa.filesystem.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: hl
 * @Date: 2018/9/1 09:07
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@SpringBootTest()
@RunWith(SpringRunner.class)
public class FileServiceImplTest {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Test
    public void delete() {
        this.gridFsTemplate.delete(Query.query(Criteria.where("use").is(null)));
    }
}

