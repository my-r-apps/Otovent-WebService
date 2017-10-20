package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long>{
}
