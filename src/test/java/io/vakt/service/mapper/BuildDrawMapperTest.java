package io.vakt.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BuildDrawMapperTest {

    private BuildDrawMapper buildDrawMapper;

    @BeforeEach
    public void setUp() {
        buildDrawMapper = new BuildDrawMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(buildDrawMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(buildDrawMapper.fromId(null)).isNull();
    }
}
