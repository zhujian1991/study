package cn.zhu.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "student")
@TableName(value =" student", schema = "mytest")
//@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class StudentEntity implements java.io.Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键ID，自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 20)
    private Long id;

    /**
     * 名字
     */
    @Column(name = "name", length = 255)
    private String name;

    /**
     * 身高
     */
    @Column(name = "hight", length = 255)
    private int hight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }
}
