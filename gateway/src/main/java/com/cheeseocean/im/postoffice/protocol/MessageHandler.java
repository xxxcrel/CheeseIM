package com.cheeseocean.im.postoffice.protocol;

import com.cheeseocean.im.common.entity.CheeseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xxxcrel
 * @date 2024/1/4 15:23
 */
public class MessageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CheeseMessage message = (CheeseMessage) msg;
        System.out.println("Received message: " + message);
    }

    void handleMessage(CheeseMessage msg) {

    }
}
