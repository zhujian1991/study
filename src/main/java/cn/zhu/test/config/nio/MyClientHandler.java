package cn.zhu.test.config.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class MyClientHandler  extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            for (int i = 0; i < 1000; i++) {
                ByteBuf buffer = getByteBuf(ctx);
                ctx.channel().writeAndFlush(buffer);
            }
        }
        private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
            byte[] bytes = "你好，我的名字是1234567!".getBytes(Charset.forName("utf-8"));
            ByteBuf buffer = ctx.alloc().buffer();
            buffer.writeBytes(bytes);
            return buffer;
        }
}
