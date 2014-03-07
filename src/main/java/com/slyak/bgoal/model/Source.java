/**
 * Project name : bgoal
 * File name : Source.java
 * Package name : com.slyak.bgoal.model
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.slyak.bgoal.enums.SourceType;
import com.slyak.bgoal.enums.Status;

@Entity
@Table(name="t_source")
public class Source {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String url;
	
	@Column
	@Lob
	private String info;
	
	@Column(name="source_type")
	private SourceType type;
	
	@Column(name="status")
	private Status status = Status.ENABLED;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public SourceType getType() {
		return type;
	}
	public void setType(SourceType type) {
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
