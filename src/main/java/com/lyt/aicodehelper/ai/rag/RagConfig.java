package com.lyt.aicodehelper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RagConfig {
    @Resource
    private EmbeddingModel qwenEmbeddingModel;
    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;


    @Bean
    public ContentRetriever contentRetriever(){
        //------RAG-----检索增强生成
        //1.加载文档
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
        //2。文档切割 最大1000个字符，最大重叠200
        DocumentByParagraphSplitter documentByParagraphSplitter
                = new DocumentByParagraphSplitter(1000, 200);
        //3.自定义文档加载器把文档转换成向量
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                .textSegmentTransformer(textSegment -> TextSegment.from(textSegment.metadata().getString("file_name")
                        + "\n" + textSegment.text(), textSegment.metadata()))
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        //4加载文档
        ingestor.ingest(documents);
        //5.自定义内容加载器
        EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(qwenEmbeddingModel)
                .maxResults(5)
                .minScore(0.75)
                .build();
        return contentRetriever;

    }
}
