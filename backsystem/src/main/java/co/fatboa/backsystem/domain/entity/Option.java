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
public class Option {
    @Id
    private String id;
    private List<String> headimgs; //顶部图片
    private String about; //公司介绍
    private String contact; //公司联系
    private String copyright; //网站著作权

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHeadimgs() {
        return headimgs;
    }

    public void setHeadimgs(List<String> headimgs) {
        this.headimgs = headimgs;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
