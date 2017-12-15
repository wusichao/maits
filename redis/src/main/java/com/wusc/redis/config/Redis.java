package com.wusc.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

import javax.annotation.PreDestroy;

@Configuration
@ConfigurationProperties(prefix="redis")
public class Redis {
	private String host;
	private Integer port;
	private Integer timeout;
	private JedisPool pool;
	private String password;
	private Integer database = 0;

	private GenericObjectPoolConfig config = new GenericObjectPoolConfig();

	@Bean
	JedisPool jedisPool() {
		pool = new JedisPool(config, host, port, timeout, password, database);
		return pool;
	}

	@PreDestroy
	void closeJedisPool() {
		pool.close();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public GenericObjectPoolConfig getConfig() {
		return config;
	}

	public void setConfig(GenericObjectPoolConfig config) {
		this.config = config;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDatabase() {
		return database;
	}

	public void setDatabase(Integer database) {
		this.database = database;
	}
}
