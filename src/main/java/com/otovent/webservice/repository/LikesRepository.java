package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.enums.LikesDependency;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long>{
    List<Likes> findAllByIdTargetAndLikesDependencyAndStatusEntity(Long idTarget, LikesDependency likesDependency,
                                                                   StatusEntity statusEntity);
    Likes findByCreatedDateAndStatusEntity(Date createdDate, StatusEntity statusEntity);
}
