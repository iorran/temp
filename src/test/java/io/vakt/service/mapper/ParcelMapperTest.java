package io.vakt.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ParcelMapperTest {

    private ParcelMapper parcelMapper;

    @BeforeEach
    public void setUp() {
        parcelMapper = new ParcelMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(parcelMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(parcelMapper.fromId(null)).isNull();
    }
}
