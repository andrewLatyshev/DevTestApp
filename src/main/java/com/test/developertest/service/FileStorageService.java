package com.test.developertest.service;

import com.test.developertest.models.FilesStorage;
import org.springframework.core.io.Resource;

public interface FileStorageService {

    public Resource loadFileAsResource(String fileName);


}
