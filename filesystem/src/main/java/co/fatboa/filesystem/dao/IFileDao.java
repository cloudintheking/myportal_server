package co.fatboa.filesystem.dao;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.data.mongodb.core.query.Query;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: hl
 * @Description: 文件操作接口
 * @Date: 16:14 2018/8/25
 * @Modified By:
 * @Version 1.0
 */


public interface IFileDao {
    /**
     * 文件存储
     *
     * @param inputStream
     * @param filename
     * @return
     */
    GridFSFile store(InputStream inputStream, String filename);

    /**
     * 查询单个文件
     *
     * @param query
     * @return
     */
    GridFSDBFile findOne(Query query);

    /**
     * 查询多个文件
     *
     * @param query
     * @return
     */
    List<GridFSDBFile> find(Query query);

    /**
     * 删除文件
     *
     * @param query
     */
    void delete(Query query);
}
