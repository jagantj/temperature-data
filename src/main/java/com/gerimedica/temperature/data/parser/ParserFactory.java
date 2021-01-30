package com.gerimedica.temperature.data.parser;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.gerimedica.temperature.data.model.FileType;

public class ParserFactory {

    public static Parser getParser(final MultipartFile multipartFile) {

        final var parserMap = new HashMap<FileType, Parser>();
        parserMap.put(FileType.CSV, new CSVParser());

        return parserMap.get(FileType.from(multipartFile.getContentType()));
    }
}
