package com.consume.consumeserver.service;

import com.consume.consumeserver.provider.HdfsProvider;
import lombok.RequiredArgsConstructor;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class HdfsSaverServiceImpl implements HdfsSaverService{
    private final HdfsProvider hdfsProvider;

    @Override
    public boolean save(String data) {
        Path path = new Path(hdfsProvider.getHdfsProperties().getFileName());

        try{
            FSDataOutputStream fsdos = hdfsProvider.getHdfs().create(path, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fsdos, StandardCharsets.UTF_8));
            bw.write(data);
            bw.close();
            fsdos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
