package com.demo.mongo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="t_user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 194751329394174207L;
	@Id
	private String id;
	private String name;
	private Integer age;
	private Long time;
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
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
}
