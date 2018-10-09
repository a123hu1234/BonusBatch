package com.netty.server;

import com.netty.api.CommService;
import com.netty.comm.ServerConstant;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ShorTurlServer implements CommService{

	@Override
	public void startServer() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(ServerConstant.BIZGROUPSIZE);
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(4);
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup);
		b.channel(NioServerSocketChannel.class);
		b.option(ChannelOption.SO_BACKLOG, 1024);
		b.option(ChannelOption.TCP_NODELAY, true);
		b.handler(new LoggingHandler(LogLevel.INFO));
		//b.childHandler(this.serverInitializer);
		
	}
	
}
