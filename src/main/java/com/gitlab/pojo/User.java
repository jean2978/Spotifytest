package com.gitlab.pojo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String country;
    private String email;
    private String id;
    private List<Image> images;

}
