package com.lyt.aicodehelper.ai;

import com.lyt.aicodehelper.ai.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import java.util.List;

@InputGuardrails({SafeInputGuardrail.class})
public interface AiCodeHelperService {
    @SystemMessage(fromResource="system-prompt.txt")
    String chat(String userMessage);
    @SystemMessage(fromResource ="system-prompt.txt")
    Report chatForReport(String userMessage);
    //学习报告
    record  Report(String name, List<String> suggestionList){};
    //返回封装后的结果
    @SystemMessage(fromResource="system-prompt.txt")
    Result<String> chatwithRag(String userMessage);

    @SystemMessage(fromResource="system-prompt.txt")
    Flux<String> chatStream(@MemoryId int memoryId,@UserMessage String message);
}
