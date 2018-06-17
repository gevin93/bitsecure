package com.bitsecure.syncserver.controllers;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

@RestController("/v1/sync/notify")
public class NotificationController {

    private static Logger LOG = LoggerFactory.getLogger(NotificationController.class);

    @Value("#{environment.FILE_LOCATION}")
    private String fileLocation;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void onUpdate(@RequestParam("ip") String ip, @RequestParam(required = false, defaultValue = "8080") int port) {
        String path = String.format("http://%s:%d/v1/sync/file", ip, port);
        LOG.info("Downloading file from : {}", path);

        try {
            URL url = new URL(path);
            InputStream is = url.openStream();

            File file = new File(fileLocation);
            if (file.exists()) {
                file.delete();
            }

            IOUtils.copy(is, new FileOutputStream(file));

        } catch (java.io.IOException e) {
            LOG.error("Unable to download file from : {}", path);
        }

    }

}
