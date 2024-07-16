package com.alura.forohub.Foro_Hub.Servicios;

import com.alura.forohub.Foro_Hub.Excepciones.TopicException;
import com.alura.forohub.Foro_Hub.Excepciones.UserException;
import com.alura.forohub.Foro_Hub.Http.Request.TopicRequest;
import com.alura.forohub.Foro_Hub.Http.Response.TopicResponse;
import com.alura.forohub.Foro_Hub.Modelos.Topic;
import com.alura.forohub.Foro_Hub.Utils.TopicUt;





import com.alura.forohub.Foro_Hub.Repositorios.ITopicRepo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServ {

    @Autowired
    private ITopicRepo ITopicRepo;

    public TopicResponse createTopic(TopicRequest topicRequest) throws UserException {
        Topic topic = TopicUt.toTopic(topicRequest);
        ITopicRepo.save(topic);
        return TopicUt.toTopicResponse(topic);
    }

    public TopicResponse updateTopic(String id, TopicRequest topicRequest) throws TopicException {
        Topic topic = ITopicRepo.findById(id).orElseThrow(
                () -> new TopicException("Topic not found")
        );
        topic.setTitle(topicRequest.getTitle());
        topic.setBody(topicRequest.getBody());
        ITopicRepo.save(topic);
        return TopicUt.toTopicResponse(topic);
    }

    @Transactional
    public void deleteTopic(String id) throws TopicException {
        ITopicRepo.findById(id).orElseThrow(
                () -> new TopicException("Topic not found")
        ).setEnable(false);
    }

    public TopicResponse getTopic(String id) throws TopicException {
        return TopicUt.toTopicResponse(ITopicRepo.findById(id).orElseThrow(
                () -> new TopicException("Topic not found")
        ));
    }

    public List<TopicResponse> getMyTopics(String id) {
        return ITopicRepo.findByAuthorIdAndEnable(id, true).stream()
                .map(TopicUt::toTopicResponse)
                .toList();
    }

    public List<TopicResponse> getTopics() {
        return ITopicRepo.findByEnable(true).stream()
                .map(TopicUt::toTopicResponse)
                .toList();
    }

    public List<TopicResponse> getAllTopics() {
        return ITopicRepo.findAll().stream()
                .map(TopicUt::toTopicResponse)
                .toList();
    }

}
