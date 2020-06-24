package io.vakt.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class ParcelDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParcelDTO.class);
        ParcelDTO parcelDTO1 = new ParcelDTO();
        parcelDTO1.setId(1L);
        ParcelDTO parcelDTO2 = new ParcelDTO();
        assertThat(parcelDTO1).isNotEqualTo(parcelDTO2);
        parcelDTO2.setId(parcelDTO1.getId());
        assertThat(parcelDTO1).isEqualTo(parcelDTO2);
        parcelDTO2.setId(2L);
        assertThat(parcelDTO1).isNotEqualTo(parcelDTO2);
        parcelDTO1.setId(null);
        assertThat(parcelDTO1).isNotEqualTo(parcelDTO2);
    }
}
