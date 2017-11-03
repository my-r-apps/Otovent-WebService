package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.enums.LikesDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long>{
    List<Likes> findAllByIdTargetAndLikesDependency(Long idTarget, LikesDependency likesDependency);
    Likes findByCreatedDate(Date createdDate);
}
