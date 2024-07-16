package com.alura.forohub.Foro_Hub.Repositorios;


import com.alura.forohub.Foro_Hub.Modelos.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicRepo extends JpaRepository<Topic, String> {

    List<Topic> findByAuthorIdAndEnable(String authorId, boolean enable);

    List<Topic> findByEnable(boolean enable);

}