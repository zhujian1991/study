package cn.zhu.test.entity;

import org.hibernate.annotations.DynamicInsert;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Table;
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
@Table(name = "user")
@ApiModel("用户表")
public class UserEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@TableId(value="id",type=IdType.AUTO)
	@Column(name = "id", nullable = false, unique = true, length = 11)
	@ApiModelProperty(value = "")
	private Integer id;

	/**
	 * 
	 */
	@TableField("name")
	@Column(name = "name", length = 10)
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
		object.put("name", getName());
		return object.toJSONString();
	}

}
