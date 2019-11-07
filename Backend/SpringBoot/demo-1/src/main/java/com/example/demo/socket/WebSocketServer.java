package com.example.demo.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.orderhistory.OrderHistoryService;
import com.example.demo.user.UserService;
import com.example.demo.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;


@ServerEndpoint(value = "/getUser/{username}")
@Component
public class WebSocketServer {

	// Store all socket session and their corresponding username.
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	
	private static UserService userService;
	private static OrderHistoryService orderService;

	@Autowired
	public void setUsersService(UserService service) {
	      this.userService = service;
	}
	
	@Autowired
	public void setOrderService(OrderHistoryService orderService) {
		this.orderService = orderService;
	}
	
	@OnOpen
	public void onOpen(Session session, @PathParam(value = "username") String username) throws IOException {
		usernameSessionMap.put(username, session);
		logger.info(username + " has joined");
	}

	@OnMessage
	public void onMessage(Session session, @PathParam(value = "username") String username, String message) throws IOException {
// Handle new messages
		ObjectMapper toJson = new ObjectMapper();
		String destUsername;
		Object toSend;
		if(message.startsWith("@")) {
			destUsername = message.split(" ")[0].substring(1);
		}
		else {
			destUsername = username;
		}
		if(message.contains("order")) {
			toSend = orderService.getOrderByIsActiveAndCustomerName(destUsername);
		}
		else {
			toSend = userService.getUserByUsername(destUsername);
		}
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(toJson.writeValueAsString(toSend));
			//usernameSessionMap.get(username).getBasicRemote().sendText("Hi, " + username);
		} catch (IllegalArgumentException e) {
			logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam(value = "username") String username) throws IOException {
		usernameSessionMap.remove(username);
		logger.info(username + " has left");
	}

	@OnError
	public void onError(Session session, @PathParam(value = "username") String username, Throwable throwable) {
		logger.info("Ya fuqed up: " + throwable.getMessage());
		try {
		usernameSessionMap.get(username).getBasicRemote().sendText("Ya fuqed up: " + throwable.getMessage());
		}
		catch(IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
		}

	}
}

