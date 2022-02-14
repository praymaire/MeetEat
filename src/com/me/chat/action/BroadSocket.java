package com.me.chat.action;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/broadcasting")
public class BroadSocket {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		System.out.println("message + " +message);
		
		synchronized (clients) {
			for(Session client : clients) {
				if(!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("session : "+session);
		clients.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
}
