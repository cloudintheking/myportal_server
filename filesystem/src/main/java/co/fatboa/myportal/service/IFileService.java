package co.fatboa.myportal.service;

import co.fatboa.myportal.domain.params.FileParams;

import com.mongodb.gridfs.GridFSDBFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: hl
 * @Description: 文件服务接口
 * @Date: 18:01 2018/8/25
 * @Modified By:
 * @Version 1.0
 */
public interface IFileService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    List<Map> save(MultipartFile... file) throws IOException;

    /**
     * 查询单个文件
     *
     * @param fileParams
     * @return
     */
    GridFSDBFile findOne(FileParams fileParams) throws FileNotFoundException;

    /**
     * 查询多个文件
     *
     * @param fileParams
     * @return
     */
    List<GridFSDBFile> findAll(FileParams fileParams);

    /**
     * 删除文件
     */
    void delete(String... ids) throws Exception;
}
