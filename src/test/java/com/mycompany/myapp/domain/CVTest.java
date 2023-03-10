package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CVTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CV.class);
        CV cV1 = new CV();
        cV1.setId(1L);
        CV cV2 = new CV();
        cV2.setId(cV1.getId());
        assertThat(cV1).isEqualTo(cV2);
        cV2.setId(2L);
        assertThat(cV1).isNotEqualTo(cV2);
        cV1.setId(null);
        assertThat(cV1).isNotEqualTo(cV2);
    }
}
