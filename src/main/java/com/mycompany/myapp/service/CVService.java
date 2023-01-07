package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CVDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CV}.
 */
public interface CVService {
    /**
     * Save a cV.
     *
     * @param cVDTO the entity to save.
     * @return the persisted entity.
     */
    CVDTO save(CVDTO cVDTO);

    /**
     * Partially updates a cV.
     *
     * @param cVDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CVDTO> partialUpdate(CVDTO cVDTO);

    /**
     * Get all the cVS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CVDTO> findAll(Pageable pageable);

    /**
     * Get the "id" cV.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CVDTO> findOne(Long id);

    /**
     * Delete the "id" cV.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<CVDTO> findAllByUser(Pageable pageable, Long id);

    Optional<CVDTO> findOneByUser(Long id, Long userId);

    CVDTO saveByUser(CVDTO cVDTO, Long userId);
}
