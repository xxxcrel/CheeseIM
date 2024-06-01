package com.cheeseocean.im.queue;

/**
 * @author xxxcrel
 * @date 2024/1/4 16:29
 */
public interface AbstractQueueConsumer {

    void subscribe(String queueName);

    PullResult poll();
}
