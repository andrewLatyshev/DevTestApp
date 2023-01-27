package com.test.developertest.utils;

public class FilesNotFoundException extends RuntimeException {

        public FilesNotFoundException(String message) {
            super(message);
        }

        public FilesNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
}
