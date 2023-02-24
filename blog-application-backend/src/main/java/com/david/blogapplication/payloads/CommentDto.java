package com.david.blogapplication.payloads;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
public class CommentDto {
    private int id;
    private String content;
}
