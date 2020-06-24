package io.vakt.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlaceholderParcelMapperTest {

    private PlaceholderParcelMapper placeholderParcelMapper;

    @BeforeEach
    public void setUp() {
        placeholderParcelMapper = new PlaceholderParcelMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(placeholderParcelMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(placeholderParcelMapper.fromId(null)).isNull();
    }
}
