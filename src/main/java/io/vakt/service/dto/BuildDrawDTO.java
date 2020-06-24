package io.vakt.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link io.vakt.domain.BuildDraw} entity.
 */
public class BuildDrawDTO implements Serializable {
    
    private Long id;

    private String fakeProp;


    private Long legNominationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFakeProp() {
        return fakeProp;
    }

    public void setFakeProp(String fakeProp) {
        this.fakeProp = fakeProp;
    }

    public Long getLegNominationId() {
        return legNominationId;
    }

    public void setLegNominationId(Long legNominationId) {
        this.legNominationId = legNominationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BuildDrawDTO)) {
            return false;
        }

        return id != null && id.equals(((BuildDrawDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BuildDrawDTO{" +
            "id=" + getId() +
            ", fakeProp='" + getFakeProp() + "'" +
            ", legNominationId=" + getLegNominationId() +
            "}";
    }
}
