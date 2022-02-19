package com.es.example.future;

import com.es.example.utils.Print;

import java.util.concurrent.*;

public class CompletableFutureTest {

    /**
     * 参考 https://blog.csdn.net/winterking3/article/details/115952149
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        /**
         *
         * CompletableFuture任务提交方式1 有返回值
         */
        CompletableFuture<String>  future = CompletableFuture.supplyAsync(()->{
            try {
                Print.consolePrint("task start");
                Thread.sleep(2000);
                Print.consolePrint("task finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        },executor);
        Print.consolePrint(future.get());

        /**
         *
         * CompletableFuture任务提交方式2 无返回值
         */
        CompletableFuture  future2 = CompletableFuture.runAsync(()->{
            try {
                Print.consolePrint("task start");
                Thread.sleep(2000);
                Print.consolePrint("task finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
        },executor);

        /**
         * 回调函数
         * 1. thenApply 转换结果：接受上一个线程的结果进行运算，再返回结果
         * 2. thenAccept 消费结果：接受上一个线程的结果进行运算，不反悔结果
         * 3. thenRun 任务完成后触发的回调：没有如参，也没有返回
         */
        future2.thenRun(()->{
            System.out.println("run finish.");
        });

        executor.shutdown();
    }
}
