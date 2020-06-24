package io.vakt.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link io.vakt.domain.NominationMetadata} entity.
 */
public class NominationMetadataDTO implements Serializable {
    
    private Long id;

    private String fakeProp;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NominationMetadataDTO)) {
            return false;
        }

        return id != null && id.equals(((NominationMetadataDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NominationMetadataDTO{" +
            "id=" + getId() +
            ", fakeProp='" + getFakeProp() + "'" +
            "}";
    }
}
