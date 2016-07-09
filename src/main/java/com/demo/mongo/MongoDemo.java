package com.demo.mongo;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.demo.mongo.model.User;

public class MongoDemo {
	@Autowired
	private MongoTemplate mongoTemplate;
	public void start(){
		System.out.println("************************************测试************************************");
		
		Criteria criatira = new Criteria();
		criatira.andOperator(Criteria.where("age").gt(10));
		List<User> list = mongoTemplate.find(new Query(criatira).limit(10), User.class);
		for(User user : list){
			System.out.println(MessageFormat.format("用户名：{0}，年龄：{1}，创建时间：{2}",
					user.getName(),user.getAge(),user.getTime()));
		}
//		for(int i=0;i<1000;i++){
//			ThreadPoolUtils.getExecutor().execute(new UserRunable(mongoTemplate,i));
//		}
		System.out.println("************************************end************************************");
	}
}
