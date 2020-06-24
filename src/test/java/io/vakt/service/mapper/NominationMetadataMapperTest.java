package io.vakt.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NominationMetadataMapperTest {

    private NominationMetadataMapper nominationMetadataMapper;

    @BeforeEach
    public void setUp() {
        nominationMetadataMapper = new NominationMetadataMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nominationMetadataMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nominationMetadataMapper.fromId(null)).isNull();
    }
}
