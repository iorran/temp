package io.vakt.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LegNominationMapperTest {

    private LegNominationMapper legNominationMapper;

    @BeforeEach
    public void setUp() {
        legNominationMapper = new LegNominationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(legNominationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(legNominationMapper.fromId(null)).isNull();
    }
}
