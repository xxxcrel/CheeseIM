package com.cheeseocean.im.queue;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xxxcrel
 * @date 2024/1/4 16:49
 */
@Getter
@Setter
public class PullResult {

    private String queueName;

    private String key;

    private byte[] value;

}
