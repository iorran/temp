package io.vakt.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class PlaceholderParcelDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlaceholderParcelDTO.class);
        PlaceholderParcelDTO placeholderParcelDTO1 = new PlaceholderParcelDTO();
        placeholderParcelDTO1.setId(1L);
        PlaceholderParcelDTO placeholderParcelDTO2 = new PlaceholderParcelDTO();
        assertThat(placeholderParcelDTO1).isNotEqualTo(placeholderParcelDTO2);
        placeholderParcelDTO2.setId(placeholderParcelDTO1.getId());
        assertThat(placeholderParcelDTO1).isEqualTo(placeholderParcelDTO2);
        placeholderParcelDTO2.setId(2L);
        assertThat(placeholderParcelDTO1).isNotEqualTo(placeholderParcelDTO2);
        placeholderParcelDTO1.setId(null);
        assertThat(placeholderParcelDTO1).isNotEqualTo(placeholderParcelDTO2);
    }
}
