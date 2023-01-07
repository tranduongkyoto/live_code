package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.CV;
import com.mycompany.myapp.repository.CVRepository;
import com.mycompany.myapp.service.CVService;
import com.mycompany.myapp.service.dto.CVDTO;
import com.mycompany.myapp.service.mapper.CVMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CV}.
 */
@Service
@Transactional
public class CVServiceImpl implements CVService {

    private final Logger log = LoggerFactory.getLogger(CVServiceImpl.class);

    private final CVRepository cVRepository;

    private final CVMapper cVMapper;

    public CVServiceImpl(CVRepository cVRepository, CVMapper cVMapper) {
        this.cVRepository = cVRepository;
        this.cVMapper = cVMapper;
    }

    @Override
    public CVDTO save(CVDTO cVDTO) {
        log.debug("Request to save CV : {}", cVDTO);
        CV cV = cVMapper.toEntity(cVDTO);
        cV = cVRepository.save(cV);
        return cVMapper.toDto(cV);
    }

    @Override
    public Optional<CVDTO> partialUpdate(CVDTO cVDTO) {
        log.debug("Request to partially update CV : {}", cVDTO);

        return cVRepository
            .findById(cVDTO.getId())
            .map(existingCV -> {
                cVMapper.partialUpdate(existingCV, cVDTO);

                return existingCV;
            })
            .map(cVRepository::save)
            .map(cVMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CVDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CVS");
        return cVRepository.findAll(pageable).map(cVMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CVDTO> findOne(Long id) {
        log.debug("Request to get CV : {}", id);
        return cVRepository.findById(id).map(cVMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CV : {}", id);
        cVRepository.deleteById(id);
    }
}
