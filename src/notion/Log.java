package notion;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
	static Logger logger = Logger.getLogger(Log.class.getName());
	static LogManager logManager = LogManager.getLogManager();
	static {
		try {
			// getClassLoader 是获得 .class 所在目录的上一级目录（bin）作为当前路径
			// getResource 是获得 .class 所在的目录（bin/notion）作为当前路径
			logManager.readConfiguration(Log.class.getClassLoader().getResourceAsStream("log.properties"));
			logManager.addLogger(logger);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		logger.severe("严重");
		logger.warning("警告");
		logger.info("通知");
		logger.config("配置");
		logger.fine("良好");
		logger.finer("更好");
		logger.finest("最好");
	}
}
