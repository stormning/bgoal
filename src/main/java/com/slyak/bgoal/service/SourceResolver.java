/**
 * Project name : bgoal
 * File name : SourceResolver.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import com.slyak.bgoal.enums.SourceType;

public interface SourceResolver extends SourceHandler{

	boolean support(SourceType type);
}
