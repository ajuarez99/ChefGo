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

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryService;
import com.example.demo.user.UserService;

@ServerEndpoint(value = "/notification/{username}")
@Component
public class WebSocketServer2 {

	private static UserService userService;
	private static OrderHistoryService orderService;

	@Autowired
	public void setUsersService(UserService service) {
	      WebSocketServer2.userService = service;
	}
	
	@Autowired
	public void setOrderService(OrderHistoryService orderService) {
		WebSocketServer2.orderService = orderService;
	}

	private static Map<Session, String> sessionUsernameMap = new HashMap<>();
    private static Map<String, Session> usernameSessionMap = new HashMap<>();
    
    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException 
    {
    	sessionUsernameMap.put(session, username);
    	usernameSessionMap.put(username, session);
    }
 
    @OnMessage
    public void onMessage(Session session, String message) throws IOException 
    {
    	try {
    		int oid = Integer.parseInt(message);
    		OrderHistory order = orderService.getOrderByOid(oid);
    		//TODO: Error handling if customer does not exist
    		Session customer = usernameSessionMap.get(order.getCustomer().getUsername());
    		String response = order.getChef().getName() + " has accepted your order";
    		customer.getBasicRemote().sendText(response);
    	}
    	catch(NumberFormatException e) {
    		logger.info("Message was not a number");
    	}
    	
    	
    	
    }
 
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) throws IOException
    {
    	usernameSessionMap.remove(username);
    	logger.info(username + " disconnected");
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    

}
