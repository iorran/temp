package io.vakt.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A NominationMetadata.
 */
@Entity
@Table(name = "nomination_metadata")
public class NominationMetadata implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fake_prop")
    private String fakeProp;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFakeProp() {
        return fakeProp;
    }

    public NominationMetadata fakeProp(String fakeProp) {
        this.fakeProp = fakeProp;
        return this;
    }

    public void setFakeProp(String fakeProp) {
        this.fakeProp = fakeProp;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NominationMetadata)) {
            return false;
        }
        return id != null && id.equals(((NominationMetadata) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NominationMetadata{" +
            "id=" + getId() +
            ", fakeProp='" + getFakeProp() + "'" +
            "}";
    }
}
