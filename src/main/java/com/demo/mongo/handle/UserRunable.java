package com.demo.mongo.handle;

import java.text.MessageFormat;
import java.util.Random;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.demo.mongo.Constant;
import com.demo.mongo.model.User;

public class UserRunable implements Runnable{
	private MongoTemplate mongoTemplate;
	private Random random = new Random();
	private int index;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.err.println(MessageFormat.format("线程{0}开始执行", index));
		for(int i=0;i<1000000;i++){
			User user = new User();
			user.setAge(random.nextInt(99)+1);
			user.setName(String.valueOf(Constant.name.charAt(random.nextInt(Constant.name.length())))
					+ String.valueOf(Constant.number.charAt(random.nextInt(Constant.number.length()))));
			user.setTime(System.currentTimeMillis());
			mongoTemplate.insert(user);
		}
		System.err.println(MessageFormat.format("线程{0}结束执行", index));
	}
	public UserRunable(MongoTemplate mongoTemplate,int index) {
		super();
		this.mongoTemplate = mongoTemplate;
		this.index = index;
	}

}
