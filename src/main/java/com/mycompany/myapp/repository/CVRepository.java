package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CV;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CV entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CVRepository extends JpaRepository<CV, Long> {}
