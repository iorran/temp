package io.vakt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class PlaceholderParcelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlaceholderParcel.class);
        PlaceholderParcel placeholderParcel1 = new PlaceholderParcel();
        placeholderParcel1.setId(1L);
        PlaceholderParcel placeholderParcel2 = new PlaceholderParcel();
        placeholderParcel2.setId(placeholderParcel1.getId());
        assertThat(placeholderParcel1).isEqualTo(placeholderParcel2);
        placeholderParcel2.setId(2L);
        assertThat(placeholderParcel1).isNotEqualTo(placeholderParcel2);
        placeholderParcel1.setId(null);
        assertThat(placeholderParcel1).isNotEqualTo(placeholderParcel2);
    }
}
