package co.fatboa.backsystem.domain.dto;

import lombok.Data;

/**
 * @Auther: hl
 * @Date: 2018/9/6 10:26
 * @Description:
 * @Modified By:
 * @Version 1.0
 */
@Data
public class UserDto {
    private String name;//用户名
    private Integer age;// 年龄
    private String sex; //性别
    private String department;//部门
    private String role; //角色

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
