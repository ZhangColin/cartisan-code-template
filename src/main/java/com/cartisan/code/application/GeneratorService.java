package com.cartisan.code.application;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Service
public class GeneratorService {


    public byte[] generateCode(List<String> tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        IOUtils.closeQuietly(zipOutputStream);
        return outputStream.toByteArray();
    }
}
