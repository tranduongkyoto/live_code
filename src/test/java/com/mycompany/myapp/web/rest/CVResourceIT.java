package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.CV;
import com.mycompany.myapp.repository.CVRepository;
import com.mycompany.myapp.service.dto.CVDTO;
import com.mycompany.myapp.service.mapper.CVMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CVResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CVResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_GITHUB = "AAAAAAAAAA";
    private static final String UPDATED_GITHUB = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_DES = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_DES = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_PERIOD = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_DES = "AAAAAAAAAA";
    private static final String UPDATED_JOB_DES = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSITY = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSITY = "BBBBBBBBBB";

    private static final String DEFAULT_GPA = "AAAAAAAAAA";
    private static final String UPDATED_GPA = "BBBBBBBBBB";

    private static final String DEFAULT_SKILLS = "AAAAAAAAAA";
    private static final String UPDATED_SKILLS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cvs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CVRepository cVRepository;

    @Autowired
    private CVMapper cVMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCVMockMvc;

    private CV cV;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CV createEntity(EntityManager em) {
        CV cV = new CV()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .mobile(DEFAULT_MOBILE)
            .github(DEFAULT_GITHUB)
            .linkedin(DEFAULT_LINKEDIN)
            .short_des(DEFAULT_SHORT_DES)
            .job_title(DEFAULT_JOB_TITLE)
            .company(DEFAULT_COMPANY)
            .period(DEFAULT_PERIOD)
            .job_des(DEFAULT_JOB_DES)
            .university(DEFAULT_UNIVERSITY)
            .gpa(DEFAULT_GPA)
            .skills(DEFAULT_SKILLS);
        return cV;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CV createUpdatedEntity(EntityManager em) {
        CV cV = new CV()
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .github(UPDATED_GITHUB)
            .linkedin(UPDATED_LINKEDIN)
            .short_des(UPDATED_SHORT_DES)
            .job_title(UPDATED_JOB_TITLE)
            .company(UPDATED_COMPANY)
            .period(UPDATED_PERIOD)
            .job_des(UPDATED_JOB_DES)
            .university(UPDATED_UNIVERSITY)
            .gpa(UPDATED_GPA)
            .skills(UPDATED_SKILLS);
        return cV;
    }

    @BeforeEach
    public void initTest() {
        cV = createEntity(em);
    }

    @Test
    @Transactional
    void createCV() throws Exception {
        int databaseSizeBeforeCreate = cVRepository.findAll().size();
        // Create the CV
        CVDTO cVDTO = cVMapper.toDto(cV);
        restCVMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cVDTO)))
            .andExpect(status().isCreated());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeCreate + 1);
        CV testCV = cVList.get(cVList.size() - 1);
        assertThat(testCV.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCV.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCV.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testCV.getGithub()).isEqualTo(DEFAULT_GITHUB);
        assertThat(testCV.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testCV.getShort_des()).isEqualTo(DEFAULT_SHORT_DES);
        assertThat(testCV.getJob_title()).isEqualTo(DEFAULT_JOB_TITLE);
        assertThat(testCV.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testCV.getPeriod()).isEqualTo(DEFAULT_PERIOD);
        assertThat(testCV.getJob_des()).isEqualTo(DEFAULT_JOB_DES);
        assertThat(testCV.getUniversity()).isEqualTo(DEFAULT_UNIVERSITY);
        assertThat(testCV.getGpa()).isEqualTo(DEFAULT_GPA);
        assertThat(testCV.getSkills()).isEqualTo(DEFAULT_SKILLS);
    }

    @Test
    @Transactional
    void createCVWithExistingId() throws Exception {
        // Create the CV with an existing ID
        cV.setId(1L);
        CVDTO cVDTO = cVMapper.toDto(cV);

        int databaseSizeBeforeCreate = cVRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCVMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVRepository.findAll().size();
        // set the field null
        cV.setName(null);

        // Create the CV, which fails.
        CVDTO cVDTO = cVMapper.toDto(cV);

        restCVMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cVDTO)))
            .andExpect(status().isBadRequest());

        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVRepository.findAll().size();
        // set the field null
        cV.setEmail(null);

        // Create the CV, which fails.
        CVDTO cVDTO = cVMapper.toDto(cV);

        restCVMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cVDTO)))
            .andExpect(status().isBadRequest());

        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCVS() throws Exception {
        // Initialize the database
        cVRepository.saveAndFlush(cV);

        // Get all the cVList
        restCVMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cV.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].github").value(hasItem(DEFAULT_GITHUB)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].short_des").value(hasItem(DEFAULT_SHORT_DES)))
            .andExpect(jsonPath("$.[*].job_title").value(hasItem(DEFAULT_JOB_TITLE)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].period").value(hasItem(DEFAULT_PERIOD)))
            .andExpect(jsonPath("$.[*].job_des").value(hasItem(DEFAULT_JOB_DES)))
            .andExpect(jsonPath("$.[*].university").value(hasItem(DEFAULT_UNIVERSITY)))
            .andExpect(jsonPath("$.[*].gpa").value(hasItem(DEFAULT_GPA)))
            .andExpect(jsonPath("$.[*].skills").value(hasItem(DEFAULT_SKILLS)));
    }

    @Test
    @Transactional
    void getCV() throws Exception {
        // Initialize the database
        cVRepository.saveAndFlush(cV);

        // Get the cV
        restCVMockMvc
            .perform(get(ENTITY_API_URL_ID, cV.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cV.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.github").value(DEFAULT_GITHUB))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN))
            .andExpect(jsonPath("$.short_des").value(DEFAULT_SHORT_DES))
            .andExpect(jsonPath("$.job_title").value(DEFAULT_JOB_TITLE))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.period").value(DEFAULT_PERIOD))
            .andExpect(jsonPath("$.job_des").value(DEFAULT_JOB_DES))
            .andExpect(jsonPath("$.university").value(DEFAULT_UNIVERSITY))
            .andExpect(jsonPath("$.gpa").value(DEFAULT_GPA))
            .andExpect(jsonPath("$.skills").value(DEFAULT_SKILLS));
    }

    @Test
    @Transactional
    void getNonExistingCV() throws Exception {
        // Get the cV
        restCVMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCV() throws Exception {
        // Initialize the database
        cVRepository.saveAndFlush(cV);

        int databaseSizeBeforeUpdate = cVRepository.findAll().size();

        // Update the cV
        CV updatedCV = cVRepository.findById(cV.getId()).get();
        // Disconnect from session so that the updates on updatedCV are not directly saved in db
        em.detach(updatedCV);
        updatedCV
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .github(UPDATED_GITHUB)
            .linkedin(UPDATED_LINKEDIN)
            .short_des(UPDATED_SHORT_DES)
            .job_title(UPDATED_JOB_TITLE)
            .company(UPDATED_COMPANY)
            .period(UPDATED_PERIOD)
            .job_des(UPDATED_JOB_DES)
            .university(UPDATED_UNIVERSITY)
            .gpa(UPDATED_GPA)
            .skills(UPDATED_SKILLS);
        CVDTO cVDTO = cVMapper.toDto(updatedCV);

        restCVMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cVDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cVDTO))
            )
            .andExpect(status().isOk());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
        CV testCV = cVList.get(cVList.size() - 1);
        assertThat(testCV.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCV.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCV.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testCV.getGithub()).isEqualTo(UPDATED_GITHUB);
        assertThat(testCV.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testCV.getShort_des()).isEqualTo(UPDATED_SHORT_DES);
        assertThat(testCV.getJob_title()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testCV.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testCV.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testCV.getJob_des()).isEqualTo(UPDATED_JOB_DES);
        assertThat(testCV.getUniversity()).isEqualTo(UPDATED_UNIVERSITY);
        assertThat(testCV.getGpa()).isEqualTo(UPDATED_GPA);
        assertThat(testCV.getSkills()).isEqualTo(UPDATED_SKILLS);
    }

    @Test
    @Transactional
    void putNonExistingCV() throws Exception {
        int databaseSizeBeforeUpdate = cVRepository.findAll().size();
        cV.setId(count.incrementAndGet());

        // Create the CV
        CVDTO cVDTO = cVMapper.toDto(cV);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCVMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cVDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cVDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCV() throws Exception {
        int databaseSizeBeforeUpdate = cVRepository.findAll().size();
        cV.setId(count.incrementAndGet());

        // Create the CV
        CVDTO cVDTO = cVMapper.toDto(cV);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCVMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cVDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCV() throws Exception {
        int databaseSizeBeforeUpdate = cVRepository.findAll().size();
        cV.setId(count.incrementAndGet());

        // Create the CV
        CVDTO cVDTO = cVMapper.toDto(cV);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCVMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cVDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCVWithPatch() throws Exception {
        // Initialize the database
        cVRepository.saveAndFlush(cV);

        int databaseSizeBeforeUpdate = cVRepository.findAll().size();

        // Update the cV using partial update
        CV partialUpdatedCV = new CV();
        partialUpdatedCV.setId(cV.getId());

        partialUpdatedCV
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .linkedin(UPDATED_LINKEDIN)
            .company(UPDATED_COMPANY)
            .period(UPDATED_PERIOD)
            .job_des(UPDATED_JOB_DES)
            .skills(UPDATED_SKILLS);

        restCVMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCV.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCV))
            )
            .andExpect(status().isOk());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
        CV testCV = cVList.get(cVList.size() - 1);
        assertThat(testCV.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCV.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCV.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testCV.getGithub()).isEqualTo(DEFAULT_GITHUB);
        assertThat(testCV.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testCV.getShort_des()).isEqualTo(DEFAULT_SHORT_DES);
        assertThat(testCV.getJob_title()).isEqualTo(DEFAULT_JOB_TITLE);
        assertThat(testCV.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testCV.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testCV.getJob_des()).isEqualTo(UPDATED_JOB_DES);
        assertThat(testCV.getUniversity()).isEqualTo(DEFAULT_UNIVERSITY);
        assertThat(testCV.getGpa()).isEqualTo(DEFAULT_GPA);
        assertThat(testCV.getSkills()).isEqualTo(UPDATED_SKILLS);
    }

    @Test
    @Transactional
    void fullUpdateCVWithPatch() throws Exception {
        // Initialize the database
        cVRepository.saveAndFlush(cV);

        int databaseSizeBeforeUpdate = cVRepository.findAll().size();

        // Update the cV using partial update
        CV partialUpdatedCV = new CV();
        partialUpdatedCV.setId(cV.getId());

        partialUpdatedCV
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .github(UPDATED_GITHUB)
            .linkedin(UPDATED_LINKEDIN)
            .short_des(UPDATED_SHORT_DES)
            .job_title(UPDATED_JOB_TITLE)
            .company(UPDATED_COMPANY)
            .period(UPDATED_PERIOD)
            .job_des(UPDATED_JOB_DES)
            .university(UPDATED_UNIVERSITY)
            .gpa(UPDATED_GPA)
            .skills(UPDATED_SKILLS);

        restCVMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCV.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCV))
            )
            .andExpect(status().isOk());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
        CV testCV = cVList.get(cVList.size() - 1);
        assertThat(testCV.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCV.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCV.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testCV.getGithub()).isEqualTo(UPDATED_GITHUB);
        assertThat(testCV.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testCV.getShort_des()).isEqualTo(UPDATED_SHORT_DES);
        assertThat(testCV.getJob_title()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testCV.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testCV.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testCV.getJob_des()).isEqualTo(UPDATED_JOB_DES);
        assertThat(testCV.getUniversity()).isEqualTo(UPDATED_UNIVERSITY);
        assertThat(testCV.getGpa()).isEqualTo(UPDATED_GPA);
        assertThat(testCV.getSkills()).isEqualTo(UPDATED_SKILLS);
    }

    @Test
    @Transactional
    void patchNonExistingCV() throws Exception {
        int databaseSizeBeforeUpdate = cVRepository.findAll().size();
        cV.setId(count.incrementAndGet());

        // Create the CV
        CVDTO cVDTO = cVMapper.toDto(cV);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCVMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cVDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cVDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCV() throws Exception {
        int databaseSizeBeforeUpdate = cVRepository.findAll().size();
        cV.setId(count.incrementAndGet());

        // Create the CV
        CVDTO cVDTO = cVMapper.toDto(cV);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCVMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cVDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCV() throws Exception {
        int databaseSizeBeforeUpdate = cVRepository.findAll().size();
        cV.setId(count.incrementAndGet());

        // Create the CV
        CVDTO cVDTO = cVMapper.toDto(cV);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCVMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(cVDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CV in the database
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCV() throws Exception {
        // Initialize the database
        cVRepository.saveAndFlush(cV);

        int databaseSizeBeforeDelete = cVRepository.findAll().size();

        // Delete the cV
        restCVMockMvc.perform(delete(ENTITY_API_URL_ID, cV.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CV> cVList = cVRepository.findAll();
        assertThat(cVList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
