package co.fatboa.myportal.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: hl
 * @Description: 链接类
 * @Date: 21:53 2018/8/27
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document
public class Link {
    @Id
    private String id;
    private String name; //链接名
    private String url; //链接地址
}
