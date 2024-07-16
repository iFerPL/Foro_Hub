package com.alura.forohub.Foro_Hub.Controles;


import com.alura.forohub.Foro_Hub.Excepciones.TopicException;
import com.alura.forohub.Foro_Hub.Excepciones.UserException;
import com.alura.forohub.Foro_Hub.Http.Request.TopicRequest;
import com.alura.forohub.Foro_Hub.Servicios.TopicServ;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class ControlTopic {

    @Autowired
    private TopicServ topicServ;

    @PreAuthorize("hasAuthority('Create_Topic')")
    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicRequest topicRequest) throws UserException {
        return ResponseEntity.ok(topicServ.createTopic(topicRequest));
    }

    @PreAuthorize("hasAuthority('Update_Topic')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable String id, @RequestBody @Valid TopicRequest topicRequest) throws TopicException {
        return ResponseEntity.ok(topicServ.updateTopic(id, topicRequest));
    }

    @PreAuthorize("hasAuthority('Delete_Topic')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable String id) throws TopicException {
        topicServ.deleteTopic(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('Read_Topic')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTopic(@PathVariable String id) throws TopicException {
        return ResponseEntity.ok(topicServ.getTopic(id));
    }

    @PreAuthorize("hasAuthority('Read_Topic')")
    @GetMapping
    public ResponseEntity<?> getTopics() {
        return ResponseEntity.ok(topicServ.getTopics());
    }

    @PreAuthorize("hasAuthority('All_Topic')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllTopics() {
        return ResponseEntity.ok(topicServ.getAllTopics());
    }

}
