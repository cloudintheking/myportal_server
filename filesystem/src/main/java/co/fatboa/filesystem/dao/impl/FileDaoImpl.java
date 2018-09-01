package co.fatboa.filesystem.dao.impl;

import co.fatboa.filesystem.dao.IFileDao;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;


/**
 * @Author: hl
 * @Description: 文件操作接口实现
 * @Date: 16:14 2018/8/25
 * @Modified By:
 * @Version 1.0
 */
@Repository
public class FileDaoImpl implements IFileDao {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    /**
     * 文件存储
     *
     * @param inputStream
     * @param filename
     * @return
     */
    @Override
    public GridFSFile store(InputStream inputStream, String filename) {
        return this.gridFsTemplate.store(inputStream, filename);
    }

    /**
     * 查询单个文件
     *
     * @param query
     * @return
     */
    @Override
    public GridFSDBFile findOne(Query query) {
        return this.gridFsTemplate.findOne(query);
    }

    /**
     * 查询多个文件
     *
     * @param query
     * @return
     */
    @Override
    public List<GridFSDBFile> find(Query query) {
        return this.gridFsTemplate.find(query);
    }

    /**
     * 删除文件
     *
     * @param query
     */
    @Override
    @Transactional
    public void delete(Query query) {
        this.gridFsTemplate.delete(query);
    }
}
