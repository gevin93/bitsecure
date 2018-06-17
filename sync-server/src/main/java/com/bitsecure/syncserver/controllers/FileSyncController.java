package com.bitsecure.syncserver.controllers;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController("/v1/sync/file")
public class FileSyncController {

    private static Logger LOG = LoggerFactory.getLogger(FileSyncController.class);

    @Value("#{environment.FILE_LOCATION}")
    private String fileLocation;

    @GetMapping
    public void download(HttpServletResponse response) {
        File file = new File(fileLocation);
        if (! file.exists()) {
            LOG.error("Unable to locate the file at : {}", fileLocation);
            response.setStatus(404);
            return;
        }

        try {
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        } catch (IOException e) {
            LOG.error("Error while transferring file", e);
        }
    }

}
