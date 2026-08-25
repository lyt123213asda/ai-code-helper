package com.lyt.aicodehelper.ai;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AiCodeHelperServiceTest {
@Resource
private  AiCodeHelperService aiCodeHelperService;
    @Test
    void chat() {
        String result=aiCodeHelperService.chat("你好，我是程序员是鱼皮");
        System.out.println(result);
    }


    @Test
    void chatwithmemory() {
        String result=aiCodeHelperService.chat("你好，我是程序员是鱼皮");
        System.out.println(result);
        result = aiCodeHelperService.chat("你好我是谁");
        System.out.println(result);
    }

    @Test
    void chatForReport() {
        String userMessage="你好，我是程序员鱼皮，学编程两年半，请帮我制定学习报告";
        AiCodeHelperService.Report report=aiCodeHelperService.chatForReport(userMessage);
        System.out.println(report);
    }

    @Test
    void chatwithRag() {
        Result<String> result = aiCodeHelperService.chatwithRag("怎么学习java有哪些常见的面试题");
        System.out.println(result.sources());
        System.out.println(result.content());
    }
    @Test
    void chatwithtools() {
        String result=aiCodeHelperService.chat("有哪些常见的计算机网络面试题？");
        System.out.println(result);

    }

    @Test
    void chatwithMcp() {
        String result=aiCodeHelperService.chat("什么是程序员鱼皮的编程导航？");
        System.out.println(result);

    }

    @Test
    void chatwithguardrail() {
        String result=aiCodeHelperService.chat("love");
        System.out.println(result);

    }

}