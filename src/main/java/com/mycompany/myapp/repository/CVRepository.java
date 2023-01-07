package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the CV entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CVRepository extends JpaRepository<CV, Long> {
    @Query(value = "select cv from CV cv where cv.userId=:id")
    Page<CV> findAllByUser(Pageable pageable, @Param("id") Long id);

    @Query(value = "select cv from CV cv where cv.id=:id and cv.userId=:userId")
    Optional<CV> findByIdByUser(@Param("id") Long id, @Param("userId") Long userId);
}
