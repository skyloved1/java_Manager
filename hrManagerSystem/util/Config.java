package 王逸群.hrManagerSystem.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties p = null;
	static{
		p = new Properties();
		try {
			p.load(new FileInputStream("mysql.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key) {
		return p.get(key).toString();
	}
}
