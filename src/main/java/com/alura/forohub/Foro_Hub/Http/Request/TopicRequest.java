package com.alura.forohub.Foro_Hub.Http.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicRequest {

    private String title;

    private String body;

    private String authorId;

}