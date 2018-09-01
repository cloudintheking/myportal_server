package co.fatboa.backsystem.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author: hl
 * @Description: 杂七杂八配置类
 * @Date: 21:56 2018/8/27
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document
public class Options {
    @Id
    private String id;
    private List<String> headimgs; //顶部图片
    private String about; //公司介绍
    private String contact; //公司联系
    private String copyright; //网址备案


}
