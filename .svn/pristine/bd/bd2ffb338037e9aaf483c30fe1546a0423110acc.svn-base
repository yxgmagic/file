package com.zhichao.admin.controller.webSocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zhichao.admin.config.WebSocketConfig;
import com.zhichao.service.core.shiro.ShiroUser;

//configurator = WebsocketConfig.class 该属性就是我上面配置的信息
@ServerEndpoint(value = "/websocket", configurator = WebSocketConfig.class)
@Component // 此注解千万千万不要忘记，它的主要作用就是将这个监听器纳入到Spring容器中进行管理
public class WebSocket {
	
	private Logger logger = Logger.getLogger(WebSocket.class);
	
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	private ShiroUser user;

	/**
	 * 连接建立成功调用的方法
	 * <p>
	 * config用来获取WebsocketConfig中的配置信息
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		this.session = session;
		// 获取WebsocketConfig.java中配置的“user”信息值
		this.user = (ShiroUser) config.getUserProperties().get("user");
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		logger.info(user.getName()+"("+user.getId()+")成功连接websocket"+"当前在线人数为:"+getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		logger.info(user.getName()+"("+user.getId()+")关闭连接websocket"+"当前在线人数为:"+getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info(user.getName()+"("+user.getId()+")连接websocket发送信息："+message);
		// 群发消息
		for (WebSocket item : webSocketSet) {
			try {
				item.sendMessage(user.getName() + ":" + message);
			} catch (IOException e) {
				logger.error(user.getName()+"("+user.getId()+")连接websocket发送信息后服务端群发失败！"+e);
			}
		}
	}

	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		logger.error(user.getName()+"("+user.getId()+")连接websocket异常！");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message) throws IOException {
		for (WebSocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocket.onlineCount--;
	}
}