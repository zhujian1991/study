package cn.zhu.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.*;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.alibaba.fastjson.JSONObject;
import javax.persistence.Id;

/**
 * <p>
 * 
 * </p>
 * 
 * @version 1.0
 * @author 程序老仁
 * @since 2020-05-31
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "animal")
@TableName(value ="animal", schema = "mytest")
@ApiModel("")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class AnimalEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * 
	 */
	@Id
	@Column(name = "id", nullable = false, unique = true, length = 11)
	@ApiModelProperty(value = "")
	private Integer id;

	/**
	 * 
	 */
	@TableField("age")
	@Column(name = "age", length = 11)
	@ApiModelProperty(value = "")
	private Integer age;

	/**
	 * 
	 */
	@TableField("body")
	@Column(name = "body", length = 255)
	@ApiModelProperty(value = "")
	private String body;

	/**
	 * 
	 */
	@TableField("name")
	@Column(name = "name", length = 255)
	@ApiModelProperty(value = "")
	private String name;

	/**
	 * 属性的公用set方法<br/>
	 * 
	 */
	public void setId(Integer value) {
		this.id = value;
    }

	/**
	 * 属性的公用get方法<br/>
	 * 
	 */
    public Integer getId() {
		return this.id;
	}

	/**
	 * 属性的公用set方法<br/>
	 * 
	 */
	public void setAge(Integer value) {
		this.age = value;
    }

	/**
	 * 属性的公用get方法<br/>
	 * 
	 */
    public Integer getAge() {
		return this.age;
	}

	/**
	 * 属性的公用set方法<br/>
	 * 
	 */
	public void setBody(String value) {
		this.body = value;
    }

	/**
	 * 属性的公用get方法<br/>
	 * 
	 */
    public String getBody() {
		return this.body;
	}

	/**
	 * 属性的公用set方法<br/>
	 * 
	 */
	public void setName(String value) {
		this.name = value;
    }

	/**
	 * 属性的公用get方法<br/>
	 * 
	 */
    public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		JSONObject object = new JSONObject();
		object.put("id", getId());
		object.put("age", getAge());
		object.put("body", getBody());
		object.put("name", getName());
		return object.toJSONString();
	}

}
