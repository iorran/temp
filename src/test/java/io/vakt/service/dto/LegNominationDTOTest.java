package io.vakt.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class LegNominationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LegNominationDTO.class);
        LegNominationDTO legNominationDTO1 = new LegNominationDTO();
        legNominationDTO1.setId(1L);
        LegNominationDTO legNominationDTO2 = new LegNominationDTO();
        assertThat(legNominationDTO1).isNotEqualTo(legNominationDTO2);
        legNominationDTO2.setId(legNominationDTO1.getId());
        assertThat(legNominationDTO1).isEqualTo(legNominationDTO2);
        legNominationDTO2.setId(2L);
        assertThat(legNominationDTO1).isNotEqualTo(legNominationDTO2);
        legNominationDTO1.setId(null);
        assertThat(legNominationDTO1).isNotEqualTo(legNominationDTO2);
    }
}
