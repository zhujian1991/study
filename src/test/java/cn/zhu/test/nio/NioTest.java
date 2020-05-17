package cn.zhu.test.nio;

import cn.zhu.test.config.nio.MyClientHandler;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;

import java.net.SocketAddress;

public class NioTest {

    @org.junit.Test
    public void getTest() {
        MyClientHandler myClientHandler = new MyClientHandler();
        ChannelHandlerContext context = null;
    }
}
