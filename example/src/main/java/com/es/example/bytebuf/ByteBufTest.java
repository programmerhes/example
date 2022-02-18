package com.es.example.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {

    public static void main() {

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(100);


    }
}
