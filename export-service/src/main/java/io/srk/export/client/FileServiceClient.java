package io.srk.export.client;

import io.srk.export.configuration.feign.FormData;
import io.srk.export.model.file.FileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-service-client", url = "${client.file-service-url}/api/files")
public interface FileServiceClient {

    @FormData
    @PostMapping
    FileDto create(@RequestPart("file") MultipartFile file);
}
