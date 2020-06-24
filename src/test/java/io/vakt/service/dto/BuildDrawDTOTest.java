package io.vakt.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class BuildDrawDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuildDrawDTO.class);
        BuildDrawDTO buildDrawDTO1 = new BuildDrawDTO();
        buildDrawDTO1.setId(1L);
        BuildDrawDTO buildDrawDTO2 = new BuildDrawDTO();
        assertThat(buildDrawDTO1).isNotEqualTo(buildDrawDTO2);
        buildDrawDTO2.setId(buildDrawDTO1.getId());
        assertThat(buildDrawDTO1).isEqualTo(buildDrawDTO2);
        buildDrawDTO2.setId(2L);
        assertThat(buildDrawDTO1).isNotEqualTo(buildDrawDTO2);
        buildDrawDTO1.setId(null);
        assertThat(buildDrawDTO1).isNotEqualTo(buildDrawDTO2);
    }
}
