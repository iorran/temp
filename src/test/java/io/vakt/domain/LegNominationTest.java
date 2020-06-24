package io.vakt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class LegNominationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LegNomination.class);
        LegNomination legNomination1 = new LegNomination();
        legNomination1.setId(1L);
        LegNomination legNomination2 = new LegNomination();
        legNomination2.setId(legNomination1.getId());
        assertThat(legNomination1).isEqualTo(legNomination2);
        legNomination2.setId(2L);
        assertThat(legNomination1).isNotEqualTo(legNomination2);
        legNomination1.setId(null);
        assertThat(legNomination1).isNotEqualTo(legNomination2);
    }
}
