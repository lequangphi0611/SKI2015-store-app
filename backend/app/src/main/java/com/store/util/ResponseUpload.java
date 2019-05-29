package com.store.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseUpload {

    private String relativePath;

    public ResponseUpload(String relativePath) {
        this.relativePath = relativePath;
    }
    
}