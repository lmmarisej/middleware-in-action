package com.debug.middleware.server.controller.lock;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/22 5:21 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataBaseLockControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 通过乐观锁进行转账，多个线程同时转账，必定出现冲突
     */

    private final AtomicBoolean fail = new AtomicBoolean(false);

    // 使用分布式乐观锁，多线程同时修改必定出现冲突
    @Test(expected = CompletionException.class)
    public void TestTakeMoneyWithOptimisticLock() throws InterruptedException {
        RequestBuilder request = get("/db/money/take").param("userId", "10010").param("amount", "-1");
        List<CompletableFuture<Void>> completableFutureList= new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                try {
                    mockMvc.perform(request)
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.msg", equalTo("成功")))
                            .andDo(print());
                } catch (Exception ignored) {
                }
            });
            completableFutureList.add(voidCompletableFuture);
        }
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).join();
    }
}
