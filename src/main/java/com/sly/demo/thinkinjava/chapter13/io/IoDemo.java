package com.sly.demo.thinkinjava.chapter13.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * @author sly
 * @time 2019年7月10日
 */
public class IoDemo {
	
	@Test
	public void test01() throws IOException {
		Matcher matcher = Pattern.compile("[\\d]").matcher("");
		FileReader fileReader = new FileReader("D:\\test\\iotest.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";
		while((line = bufferedReader.readLine()) != null) {
			matcher.reset(line);
			while (matcher.find()) {
				System.out.print(matcher.group());
			}
			System.out.println();
		}
		
		bufferedReader.close();
		fileReader.close();
	}
}

