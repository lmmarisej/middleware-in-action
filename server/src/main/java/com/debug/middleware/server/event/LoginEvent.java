package com.debug.middleware.server.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * 用户登录成功后的事件实体
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Getter
@Setter
@ToString
public class LoginEvent extends ApplicationEvent implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(LoginEvent.class);

    private String userName; //用户名
    private String loginTime; //登录时间
    private String ip; //所在ip

    /**
     * <p>Constructor for LoginEvent.</p>
     *
     * @param source a {@link java.lang.Object} object.
     */
    public LoginEvent(Object source) {
        super(source);
    }

    /**
     * <p>Constructor for LoginEvent.</p>
     *
     * @param source a {@link java.lang.Object} object.
     * @param userName a {@link java.lang.String} object.
     * @param loginTime a {@link java.lang.String} object.
     * @param ip a {@link java.lang.String} object.
     */
    public LoginEvent(Object source, String userName, String loginTime, String ip) {
        super(source);
        this.userName = userName;
        this.loginTime = loginTime;
        this.ip = ip;
    }

    /**
     * <p>hasBeenConsumed.</p>
     */
    public void hasBeenConsumed() {
        log.info("已被消费");
    }
}
