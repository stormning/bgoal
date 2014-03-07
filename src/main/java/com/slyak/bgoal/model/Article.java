/**
 * Project name : bgoal
 * File name : Article.java
 * Package name : com.slyak.bgoal.model
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.BatchSize;

import com.slyak.bgoal.enums.ArticleStatus;


@Entity
@Table(name = "t_article",uniqueConstraints = {@UniqueConstraint(columnNames={"old_url_short"})})
@BatchSize(size=40)
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String title;
	
	@Column
	private Long creator;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column
	private String content;

	@Column(name="source_id")
	private Long sourceId;

	@Column(name="old_url")
	private String oldUrl;
	
	@Column(name="old_url_short")
	private String oldUrlShort;
	
	@Column(name="img_count")
	private int imgCount;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Column(name="status")
	private ArticleStatus status = ArticleStatus.NORMAL;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getOldUrl() {
		return oldUrl;
	}

	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}

	public int getImgCount() {
		return imgCount;
	}

	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public void setOldUrlShort(String oldUrlShort) {
		this.oldUrlShort = oldUrlShort;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getOldUrlShort() {
		return oldUrlShort;
	}

	public ArticleStatus getStatus() {
		return status;
	}

	public void setStatus(ArticleStatus status) {
		this.status = status;
	}
}
