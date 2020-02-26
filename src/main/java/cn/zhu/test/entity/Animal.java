package cn.zhu.test.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * JsonIgnoreProperties 标签添加的原因：实体类中有null,导致生成json时报错
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Animal {

    @Id
    private Integer id;
    private String name;
    private String body;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
