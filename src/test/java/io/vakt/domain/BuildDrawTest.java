package io.vakt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.vakt.web.rest.TestUtil;

public class BuildDrawTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuildDraw.class);
        BuildDraw buildDraw1 = new BuildDraw();
        buildDraw1.setId(1L);
        BuildDraw buildDraw2 = new BuildDraw();
        buildDraw2.setId(buildDraw1.getId());
        assertThat(buildDraw1).isEqualTo(buildDraw2);
        buildDraw2.setId(2L);
        assertThat(buildDraw1).isNotEqualTo(buildDraw2);
        buildDraw1.setId(null);
        assertThat(buildDraw1).isNotEqualTo(buildDraw2);
    }
}
