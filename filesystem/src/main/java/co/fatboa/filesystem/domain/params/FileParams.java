package co.fatboa.filesystem.domain.params;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: hl
 * @Description: 文件查询参数
 * @Date: 17:33 2018/8/25
 * @Modified By:
 * @Version 1.0
 */
public class FileParams {
    @ApiModelProperty("文件id")
    @SerializedName("_id")
    private String id;
    @ApiModelProperty("文件md5")
    private String md5;
    @ApiModelProperty("文件名")
    private String filename;
    @ApiModelProperty("文件类型")
    private String contentType;

    public FileParams(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
