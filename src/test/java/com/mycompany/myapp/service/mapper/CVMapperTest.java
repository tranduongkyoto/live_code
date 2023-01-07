package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CVMapperTest {

    private CVMapper cVMapper;

    @BeforeEach
    public void setUp() {
        cVMapper = new CVMapperImpl();
    }
}
