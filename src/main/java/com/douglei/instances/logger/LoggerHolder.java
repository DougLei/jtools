package com.douglei.instances.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * 日志持有对象
 * <p>对Logger对象的封装</p>
 * @author StoneKing
 */
public class LoggerHolder {
	private Logger logger;
	public LoggerHolder(Class<?> clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	public String getName() {
		return logger.getName();
	}

	// ---------------------------------------------------------------------------
	// trace
	// ---------------------------------------------------------------------------
	public boolean isTraceEnabled(){
		return logger.isTraceEnabled();
	}
	
	public boolean isTraceEnabled(Marker marker){
		return logger.isTraceEnabled(marker);
	}
	
	public void trace(String msg) {
		if(logger.isTraceEnabled()){
			logger.trace(msg);
		}
	}

	public void trace(String format, Object arg) {
		if(logger.isTraceEnabled()){
			logger.trace(format, arg);
		}
	}

	public void trace(String format, Object arg1, Object arg2) {
		if(logger.isTraceEnabled()){
			logger.trace(format, arg1, arg2);
		}
	}

	public void trace(String format, Object... args) {
		if(logger.isTraceEnabled()){
			logger.trace(format, args);
		}
	}

	public void trace(String msg, Throwable t) {
		if(logger.isTraceEnabled()){
			logger.trace(msg, t);
		}
	}

	public void trace(Marker marker, String msg) {
		if(logger.isTraceEnabled(marker)){
			logger.trace(marker, msg);
		}
	}

	public void trace(Marker marker, String format, Object arg) {
		if(logger.isTraceEnabled(marker)){
			logger.trace(marker, format, arg);
		}
	}

	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		if(logger.isTraceEnabled(marker)){
			logger.trace(marker, format, arg1, arg2);
		}
	}

	public void trace(Marker marker, String format, Object... args) {
		if(logger.isTraceEnabled(marker)){
			logger.trace(marker, format, args);
		}
	}

	public void trace(Marker marker, String msg, Throwable t) {
		if(logger.isTraceEnabled(marker)){
			logger.trace(marker, msg, t);
		}
	}

	// ---------------------------------------------------------------------------
	// debug
	// ---------------------------------------------------------------------------
	public boolean isDebugEnabled(){
		return logger.isDebugEnabled();
	}
	
	public boolean isDebugEnabled(Marker marker){
		return logger.isDebugEnabled(marker);
	}
	
	public void debug(String msg) {
		if(logger.isDebugEnabled()){
			logger.debug(msg);
		}
	}

	public void debug(String format, Object arg) {
		if(logger.isDebugEnabled()){
			logger.debug(format, arg);
		}
	}

	public void debug(String format, Object arg1, Object arg2) {
		if(logger.isDebugEnabled()){
			logger.debug(format, arg1, arg2);
		}
	}

	public void debug(String format, Object... args) {
		if(logger.isDebugEnabled()){
			logger.debug(format, args);
		}
	}

	public void debug(String msg, Throwable t) {
		if(logger.isDebugEnabled()){
			logger.debug(msg, t);
		}
	}

	public void debug(Marker marker, String msg) {
		if(logger.isDebugEnabled(marker)){
			logger.debug(marker, msg);
		}
	}

	public void debug(Marker marker, String format, Object arg) {
		if(logger.isDebugEnabled(marker)){
			logger.debug(marker, format, arg);
		}
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		if(logger.isDebugEnabled(marker)){
			logger.debug(marker, format, arg1, arg2);
		}
	}

	public void debug(Marker marker, String format, Object... args) {
		if(logger.isDebugEnabled(marker)){
			logger.debug(marker, format, args);
		}
	}

	public void debug(Marker marker, String msg, Throwable t) {
		if(logger.isDebugEnabled(marker)){
			logger.debug(marker, msg, t);
		}
	}

	// ---------------------------------------------------------------------------
	// info
	// ---------------------------------------------------------------------------
	public boolean isInfoEnabled(){
		return logger.isInfoEnabled();
	}
	
	public boolean isInfoEnabled(Marker marker){
		return logger.isInfoEnabled(marker);
	}
	
	public void info(String msg) {
		if(logger.isInfoEnabled()){
			logger.info(msg);
		}
	}

	public void info(String format, Object arg) {
		if(logger.isInfoEnabled()){
			logger.info(format, arg);
		}
	}

	public void info(String format, Object arg1, Object arg2) {
		if(logger.isInfoEnabled()){
			logger.info(format, arg1, arg2);
		}
	}

	public void info(String format, Object... args) {
		if(logger.isInfoEnabled()){
			logger.info(format, args);
		}
	}

	public void info(String msg, Throwable t) {
		if(logger.isInfoEnabled()){
			logger.info(msg, t);
		}
	}

	public void info(Marker marker, String msg) {
		if(logger.isInfoEnabled(marker)){
			logger.info(marker, msg);
		}
	}

	public void info(Marker marker, String format, Object arg) {
		if(logger.isInfoEnabled(marker)){
			logger.info(marker, format, arg);
		}
	}

	public void info(Marker marker, String format, Object arg1, Object arg2) {
		if(logger.isInfoEnabled(marker)){
			logger.info(marker, format, arg1, arg2);
		}
	}

	public void info(Marker marker, String format, Object... args) {
		if(logger.isInfoEnabled(marker)){
			logger.info(marker, format, args);
		}
	}

	public void info(Marker marker, String msg, Throwable t) {
		if(logger.isInfoEnabled(marker)){
			logger.info(marker, msg, t);
		}
	}

	// ---------------------------------------------------------------------------
	// warn
	// ---------------------------------------------------------------------------
	public boolean isWarnEnabled(){
		return logger.isWarnEnabled();
	}
	
	public boolean isWarnEnabled(Marker marker){
		return logger.isWarnEnabled(marker);
	}
	
	public void warn(String msg) {
		if(logger.isWarnEnabled()){
			logger.warn(msg);
		}
	}

	public void warn(String format, Object arg) {
		if(logger.isWarnEnabled()){
			logger.warn(format, arg);
		}
	}

	public void warn(String format, Object... args) {
		if(logger.isWarnEnabled()){
			logger.warn(format, args);
		}
	}

	public void warn(String format, Object arg1, Object arg2) {
		if(logger.isWarnEnabled()){
			logger.warn(format, arg1, arg2);
		}
	}

	public void warn(String msg, Throwable t) {
		if(logger.isWarnEnabled()){
			logger.warn(msg, t);
		}
	}

	public void warn(Marker marker, String msg) {
		if(logger.isWarnEnabled(marker)){
			logger.warn(marker, msg);
		}
	}

	public void warn(Marker marker, String format, Object arg) {
		if(logger.isWarnEnabled(marker)){
			logger.warn(marker, format, arg);
		}
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		if(logger.isWarnEnabled(marker)){
			logger.warn(marker, format, arg1, arg2);
		}
	}

	public void warn(Marker marker, String format, Object... args) {
		if(logger.isWarnEnabled(marker)){
			logger.warn(marker, format, args);
		}
	}

	public void warn(Marker marker, String msg, Throwable t) {
		if(logger.isWarnEnabled(marker)){
			logger.warn(marker, msg, t);
		}
	}

	// ---------------------------------------------------------------------------
	// error
	// ---------------------------------------------------------------------------
	public boolean isErrorEnabled(){
		return logger.isErrorEnabled();
	}
	
	public boolean isErrorEnabled(Marker marker){
		return logger.isErrorEnabled(marker);
	}
	
	public void error(String msg) {
		if(logger.isErrorEnabled()){
			logger.error(msg);
		}
	}

	public void error(String format, Object arg) {
		if(logger.isErrorEnabled()){
			logger.error(format, arg);
		}
	}

	public void error(String format, Object arg1, Object arg2) {
		if(logger.isErrorEnabled()){
			logger.error(format, arg1, arg2);
		}
	}

	public void error(String format, Object... args) {
		if(logger.isErrorEnabled()){
			logger.error(format, args);
		}
	}

	public void error(String msg, Throwable t) {
		if(logger.isErrorEnabled()){
			logger.error(msg, t);
		}
	}

	public void error(Marker marker, String msg) {
		if(logger.isErrorEnabled(marker)){
			logger.error(marker, msg);
		}
	}

	public void error(Marker marker, String format, Object arg) {
		if(logger.isErrorEnabled(marker)){
			logger.error(marker, format, arg);
		}
	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
		if(logger.isErrorEnabled(marker)){
			logger.error(marker, format, arg1, arg2);
		}
	}

	public void error(Marker marker, String format, Object... args) {
		if(logger.isErrorEnabled(marker)){
			logger.error(marker, format, args);
		}
	}

	public void error(Marker marker, String msg, Throwable t) {
		if(logger.isErrorEnabled(marker)){
			logger.error(marker, msg, t);
		}
	}
}
