package co.fatboa.filesystem.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 13:46 2018/8/26
 * @Modified By:
 * @Version 1.0
 */
@Data
public class FileDTO implements Serializable {
    private Object id;
    private String filename;
    private long length;
    private long chunkSize;
    private Date uploadDate;
    private String md5;
}
