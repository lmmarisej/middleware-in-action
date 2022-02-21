package com.debug.middleware.server;

import com.debug.middleware.server.event.LoginEvent;
import com.debug.middleware.server.event.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private Publisher publisher;

    @Test
    public void test1() throws Exception {
        LoginEvent loginEvent = new LoginEvent(publisher);
        loginEvent.setLoginTime("23122");
        LoginEvent spy = spy(loginEvent);
        publisher.sendMsg(spy);
        verify(spy).hasBeenConsumed();
    }

}