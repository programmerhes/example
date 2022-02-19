package com.es.example.bytebuf;

import com.es.example.utils.Print;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.charset.StandardCharsets;

/**
 * byteBuf 测试类
 * @author kiruma
 * @date 2022/02/19
 */
public class ByteBufTest {

    public static void main(String[] args) {
        /**
         * 分配
         *
         * 池化byteBuf 可重复利用
         * 非池化byteBuf 不可重复利用，性能低
         */
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(100);

        Print.consolePrint("=========案例一=========");
        String testStr = "这是一个测试,this is a test";
        byte[] testBytes = testStr.getBytes(StandardCharsets.UTF_8);
        /**
         * 在utf-8编码下，一个中文字符占3个字节，一个英文字符占一个字节
         */
        Print.consolePrint("testStr 占用：%s 个字节。",testBytes.length);
        byteBuf.writeBytes(testBytes);
        Print.consolePrint("byteBuf readerIndex:%s,writerIndex:%s",byteBuf.readerIndex(),byteBuf.writerIndex());


        Print.consolePrint("=========案例二=========");
        /**
         * 标记读位
         */
        byteBuf.markReaderIndex();
        /**
         * 要读取多少，字符数组就设置多少
         */
        byte[] readBytes$1 = new byte[6];
        byteBuf.readBytes(readBytes$1);
        String readStr$1 = new String(readBytes$1,StandardCharsets.UTF_8);
        Print.consolePrint(readStr$1);
        Print.consolePrint("byteBuf readerIndex:%s,writerIndex:%s",byteBuf.readerIndex(),byteBuf.writerIndex());
        /**
         * 重置读位
         */
        byteBuf.resetReaderIndex();
        Print.consolePrint("byteBuf readerIndex:%s,writerIndex:%s",byteBuf.readerIndex(),byteBuf.writerIndex());


        Print.consolePrint("=========案例三=========");
        /**
         * 拷贝新的byteBuf
         * 1）内存拷贝，不可取
         * 2）浅拷贝，实际上没有拷贝，共用同一地址
         */
         ByteBuf newByteBuf = byteBuf.slice(byteBuf.readerIndex(),12);
        /**
         * 由于是共用，引用需要加1，用完释放掉
         */
        newByteBuf.retain();
        byte[] readBytes$2 = new byte[12];
        newByteBuf.readBytes(readBytes$2);
        String readStr$2 = new String(readBytes$2,StandardCharsets.UTF_8);
        Print.consolePrint(readStr$2);
        newByteBuf.release();
        Print.consolePrint("newByteBuf readerIndex:%s,writerIndex:%s",newByteBuf.readerIndex(),newByteBuf.writerIndex());
        Print.consolePrint("byteBuf readerIndex:%s,writerIndex:%s",byteBuf.readerIndex(),byteBuf.writerIndex());
    }

}
