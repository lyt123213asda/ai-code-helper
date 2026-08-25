package com.lyt.aicodehelper;

import com.lyt.aicodehelper.ai.AiCodeHelper;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperApplicationTests {
@Resource
    private AiCodeHelper aiCodeHelper;
    @Test
    void contextLoads() {
    }

    @Test
    void chat() {
        aiCodeHelper.chat("你好我是lyt");
    }

    @Test
    void ChatwithMessage() {
        UserMessage userMessage=UserMessage.from(
                TextContent.from("图片描述"),
                ImageContent.from("https://www.codefather.cn/logo.png")
        );
        aiCodeHelper.ChatwithMessage(userMessage);
    }
}
