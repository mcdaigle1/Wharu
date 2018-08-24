package com.wheru.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogService extends BaseService {
	private static final LogService instance = new LogService();
	private Logger logger = null;

	private LogService() {
		logger = LogManager.getLogger("com.wharu.log4j2");
	} 
	
	public static LogService instance() {
		return instance;
	}

	/**
	 * Write a debug message to the log.
	 * @param logMessage the message to write
	 */
	public void debug(String logMessage) { logger.debug(logMessage); }

	/**
	 *  Write a debug message and exception information to the log
	 * @param logMessage the message to write
	 * @param e the exception to display
	 */
	public void debug(String logMessage, Exception e) { 
		logger.debug(logMessage); 
		logger.debug(e);
	}

	/**
	 * Write an informational message to the log.
	 * @param logMessage the message to write
	 */
	public void info(String logMessage) { 
		logger.info(logMessage); 
	}

	/**
	 *  Write an info message and exception information to the log
	 * @param logMessage the message to write
	 * @param e the exception to display
	 */
	public void info(String logMessage, Exception e) { 
		logger.info(logMessage); 
		logger.info(e.getMessage());	
	}

	/**
	 * Write a warning message to the log.
	 * @param logMessage the message to write
	 */
	public void warn(String logMessage) { 
		logger.warn(logMessage); 
	}

	/**
	 *  Write a warning message and exception information to the log
	 * @param logMessage the message to write
	 * @param e the exception to display
	 */
	public void warn(String logMessage, Exception e) { 
		logger.warn(logMessage); 
		logger.warn(e.getMessage());
	}

	/**
	 * Write an error message to the log.
	 * @param logMessage the message to write
	 */
	public void error(String logMessage) { 
		logger.error(logMessage); 
	}

	/**
	 *  Write an error message and exception information to the log
	 * @param logMessage the message to write
	 * @param e the exception to display
	 */
	public void error(String logMessage, Exception e) { 
		logger.error(logMessage, e); 
	}

	/**
	 * Write a fatal error message to the log.
	 * @param logMessage the message to write
	 */
	public void fatal(String logMessage) { 
		logger.fatal(logMessage); 
	}

	/**
	 *  Write a fatal error message and exception information to the log
	 * @param logMessage the message to write
	 * @param e the exception to display
	 */
	public void fatal(String logMessage, Exception e) { 
		logger.fatal(logMessage); 
		logger.fatal(e);
	}

}
