package com.gitlab.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Image {
    private String url;
    private int height;
    private int width; 
}
