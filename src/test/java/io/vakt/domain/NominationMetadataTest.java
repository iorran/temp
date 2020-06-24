package io.vakt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class NominationMetadataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NominationMetadata.class);
        NominationMetadata nominationMetadata1 = new NominationMetadata();
        nominationMetadata1.setId(1L);
        NominationMetadata nominationMetadata2 = new NominationMetadata();
        nominationMetadata2.setId(nominationMetadata1.getId());
        assertThat(nominationMetadata1).isEqualTo(nominationMetadata2);
        nominationMetadata2.setId(2L);
        assertThat(nominationMetadata1).isNotEqualTo(nominationMetadata2);
        nominationMetadata1.setId(null);
        assertThat(nominationMetadata1).isNotEqualTo(nominationMetadata2);
    }
}
