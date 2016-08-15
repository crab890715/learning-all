package com.demo.beetl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import com.alibaba.fastjson.JSONObject;

public class Demo {
	public static void main(String[] args) throws IOException {
		String root = Demo.class.getResource("/").getPath();
		System.err.println(root);
		FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
		Configuration cfg = Configuration.defaultConfiguration();
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
		Map<String, Object> map = new HashMap<>();
		map.put("abc", 1);
		map.put("abc1", 1);
		map.put("abc2", 1);
		Template t = gt.getTemplate("test.html");
		t.binding("name", "螃蟹");
		t.binding("data", JSONObject.toJSON(map));
		String str = t.render();
		System.out.println(str);
	}
}
