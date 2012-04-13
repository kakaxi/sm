package com.simplemad.msgserver;

import org.apache.mina.core.service.IoHandlerAdapter;

import com.simplemad.bean.Message;
import com.simplemad.message.server.IMessageServer;
import com.simplemad.message.server.ServerHandlerAdapter;
import com.simplemad.message.server.UdpMessageServer;
import com.simplemad.parameter.MsgServerParameter;

public class SimplemadServer {
	private IMessageServer messageServer;
	
	private SimplemadServer() {
		startup();
	}
	
	static class SimplemadServerFactory {
		private static SimplemadServer instance = new SimplemadServer();
	}
	
	public static SimplemadServer instance() {
		return SimplemadServerFactory.instance;
	}
	
	public IMessageServer getServer() {
		return messageServer;
	}
	
	private void startup() {
		if(messageServer != null) {
			return;
		}
		messageServer = new UdpMessageServer(MsgServerParameter.UDP_PORT);
		IoHandlerAdapter handler = new ServerHandlerAdapter(messageServer) {
			@Override
			protected void onReceived(Message msg) {
				IMessageCommand command = CommandFactory.create(msg);
				if(command != null) {
					command.execute();
				}
			}
		};
		messageServer.setHandler(handler);
		messageServer.startup();
	}
}
