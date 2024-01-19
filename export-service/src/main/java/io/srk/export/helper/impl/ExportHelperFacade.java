package io.srk.export.helper.impl;

import io.srk.export.client.FileServiceClient;
import io.srk.export.model.export.dto.ExportRequestDto;
import io.srk.export.model.export.enumeration.ExportType;
import io.srk.export.model.export.mapper.ExportRequestMapper;
import io.srk.export.model.file.FileDto;
import io.srk.export.service.impl.BookStatisticExporter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExportHelperFacade {

    private final BookStatisticExporter bookStatisticExporter;
    private final ExportRequestMapper mapper;
    private final FileServiceClient fileServiceClient;

    public FileDto export(ExportRequestDto request) {
        byte[] data = null;
        if (request.getType() == ExportType.BOOK_SELL_STATISTIC) {
            var statisticRequest = mapper.toBookRequest(request);
            data = bookStatisticExporter.export(statisticRequest);
        }
        return saveFile(data);
    }

    private FileDto saveFile(byte[] data) {
        var multipart = new MockMultipartFile(
                "file",
                "export.xlsx",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                data
        );
        return fileServiceClient.create(multipart);
    }
}
