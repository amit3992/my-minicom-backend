package com.intercom.spring.config;

import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.document.Document;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class InMemoryVectorStoreConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public VectorStore vector(EmbeddingClient embedding) throws Exception {
        Resource json = resourceLoader.getResource("classpath:api.intercom.io.json");
        VectorStore vectors = new SimpleVectorStore(embedding);

//        InputStream is = json.getInputStream();
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(is);
//        List<Document> docs = new ArrayList<>();
//        Iterator<String> fieldNames = root.fieldNames();
//        TokenTextSplitter splitter = new TokenTextSplitter();// 512 tokens per chunk, 32 overlap
//
//        while (fieldNames.hasNext()) {
//            String endpoint = fieldNames.next();
//            JsonNode endpointNode = root.get(endpoint);
//            String description = endpointNode.has("description")
//                    ? endpointNode.get("description").asText()
//                    : endpointNode.toString();
//
//            // split into chunks
//            List<String> chunks = splitter.split(description, 2048);
//            for (String chunk : chunks) {
//                Document doc = new Document(chunk);
//                doc.getMetadata().put("id", endpoint);
//                docs.add(doc);
//            }
//        }
//        vectors.accept(docs);
        return vectors;
    }
}
