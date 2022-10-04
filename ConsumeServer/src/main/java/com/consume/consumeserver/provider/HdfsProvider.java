package com.consume.consumeserver.provider;

import com.consume.consumeserver.config.HdfsConfig;
import com.consume.consumeserver.properties.HdfsProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Getter
@Component
@RequiredArgsConstructor
public class HdfsProvider {
    private final HdfsProperties hdfsProperties;
    private final HdfsConfig hdfsConfig;
    private FileSystem hdfs;

    @PostConstruct
    protected void init() throws URISyntaxException, IOException {
        hdfs = FileSystem.get(new URI(hdfsProperties.getUri()), hdfsConfig);
    }
}
