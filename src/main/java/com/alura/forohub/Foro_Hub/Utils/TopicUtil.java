package com.alura.forohub.Foro_Hub.Utils;

import com.alura.forohub.Foro_Hub.Excepciones.UserException;
import com.alura.forohub.Foro_Hub.Http.Request.TopicRequest;
import com.alura.forohub.Foro_Hub.Http.Response.TopicResponse;
import com.alura.forohub.Foro_Hub.Modelos.Topic;
import com.alura.forohub.Foro_Hub.Modelos.Usuario;
import com.alura.forohub.Foro_Hub.Repositorios.IUsuarioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicUt {

    @Autowired
    private static IUsuarioRepo userRepository;

    public static TopicResponse toTopicResponse(Topic topic){
        return TopicResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .body(topic.getBody())
                .authorName(topic.getAuthor().getUsername())
                .build();
    }

    public static Topic toTopic(TopicRequest topicRequest) throws UserException {
        Usuario user = userRepository.findById(topicRequest.getAuthorId())
                .orElseThrow( () -> new UserException("User not found"));
        return Topic.builder()
                .title(topicRequest.getTitle())
                .body(topicRequest.getBody())
                .author(user)
                .build();
    }

}
