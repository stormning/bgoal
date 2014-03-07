/**
 * Project name : bgoal
 * File name : ArticleMaker.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import com.slyak.bgoal.model.Source;

public interface Spider {
	void fetch(Source source);
}
