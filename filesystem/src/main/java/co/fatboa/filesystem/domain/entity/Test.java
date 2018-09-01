package co.fatboa.filesystem.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 20:24 2018/8/27
 * @Modified By:
 * @Version 1.0
 */
@Data
@Document(collection = "gugu")
public class Test {
    @Id
    private String id;
    @Field("testName")
    private String testname;
    private String testAge;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getTestAge() {
        return testAge;
    }

    public void setTestAge(String testAge) {
        this.testAge = testAge;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
