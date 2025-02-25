package com.cheeseocean.im.postoffice.server;

import com.cheeseocean.im.common.codec.MessageDecoder;
import com.cheeseocean.im.common.codec.MessageEncoder;
import com.cheeseocean.im.message.api.MessageService;
import com.cheeseocean.im.postoffice.config.PostofficeProperties;
import com.cheeseocean.im.postoffice.handler.ConnectionHandler;
import com.cheeseocean.im.postoffice.handler.TimeoutHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IMServer {

    private PostofficeProperties postofficeProperties;

    private MessageService messageService;

    private EventLoopGroup boss;

    private EventLoopGroup worker;

    public IMServer() {
        this.boss = new NioEventLoopGroup(2);
        this.worker = new NioEventLoopGroup(2);
    }

    public void start() {
        ServerBootstrap boot = new ServerBootstrap();
        //@formatter:off
        boot.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("idleStateHandler", new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
                                .addAfter("idleStateHandler", "timeoutHandler", new TimeoutHandler())
                                .addLast(new MessageDecoder())
                                .addLast(MessageEncoder.INSTANCE)
                                .addLast(new ConnectionHandler(messageService));
                    }
                });
        //@formatter:on
        try {
            log.info("start im server successful, using port:{}", postofficeProperties.getPort());
            boot.bind(postofficeProperties.getPort()).syncUninterruptibly();
        } finally {
            log.info("stop im server gracefully");
        }
    }
}
