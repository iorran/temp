package io.vakt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Parcel.
 */
@Entity
@Table(name = "parcel")
public class Parcel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fake_prop")
    private String fakeProp;

    @ManyToOne
    @JsonIgnoreProperties(value = "parcels", allowSetters = true)
    private LegNomination legNomination;

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

    public Parcel fakeProp(String fakeProp) {
        this.fakeProp = fakeProp;
        return this;
    }

    public void setFakeProp(String fakeProp) {
        this.fakeProp = fakeProp;
    }

    public LegNomination getLegNomination() {
        return legNomination;
    }

    public Parcel legNomination(LegNomination legNomination) {
        this.legNomination = legNomination;
        return this;
    }

    public void setLegNomination(LegNomination legNomination) {
        this.legNomination = legNomination;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parcel)) {
            return false;
        }
        return id != null && id.equals(((Parcel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Parcel{" +
            "id=" + getId() +
            ", fakeProp='" + getFakeProp() + "'" +
            "}";
    }
}
