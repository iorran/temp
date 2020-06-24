package io.vakt.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class NominationMetadataDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NominationMetadataDTO.class);
        NominationMetadataDTO nominationMetadataDTO1 = new NominationMetadataDTO();
        nominationMetadataDTO1.setId(1L);
        NominationMetadataDTO nominationMetadataDTO2 = new NominationMetadataDTO();
        assertThat(nominationMetadataDTO1).isNotEqualTo(nominationMetadataDTO2);
        nominationMetadataDTO2.setId(nominationMetadataDTO1.getId());
        assertThat(nominationMetadataDTO1).isEqualTo(nominationMetadataDTO2);
        nominationMetadataDTO2.setId(2L);
        assertThat(nominationMetadataDTO1).isNotEqualTo(nominationMetadataDTO2);
        nominationMetadataDTO1.setId(null);
        assertThat(nominationMetadataDTO1).isNotEqualTo(nominationMetadataDTO2);
    }
}
