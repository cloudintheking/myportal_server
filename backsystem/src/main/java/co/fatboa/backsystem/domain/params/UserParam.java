package co.fatboa.backsystem.domain.params;

import co.fatboa.core.domain.queryparams.BaseQueryParam;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: hl
 * @Date: 2018/9/6 10:07
 * @Description: 用户查询参数
 * @Modified By:
 * @Version 1.0
 */
public class UserParam extends BaseQueryParam {
    @ApiModelProperty("用户ID")
    private String id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("登录密码")
    private String password;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("部门")
    private String department;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
