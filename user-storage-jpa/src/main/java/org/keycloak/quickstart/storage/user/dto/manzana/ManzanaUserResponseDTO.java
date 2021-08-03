package org.keycloak.quickstart.storage.user.dto.manzana;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ManzanaUserResponseDTO {

    public ManzanaUserResponseDTO() {
    }

    public ManzanaUserResponseDTO(String odataMetadata, List<ManzanaUserDTO> value) {
        this.odataMetadata = odataMetadata;
        this.value = value;
    }

    @JsonProperty("odata.metadata")
    private String odataMetadata;

    private List<ManzanaUserDTO> value;

    public String getOdataMetadata() {
        return odataMetadata;
    }

    public void setOdataMetadata(String odataMetadata) {
        this.odataMetadata = odataMetadata;
    }

    public List<ManzanaUserDTO> getValue() {
        return value;
    }

    public void setValue(List<ManzanaUserDTO> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ManzanaUserResponseDTO{" +
                "odataMetadata='" + odataMetadata + '\'' +
                ", value=" + value +
                '}';
    }
}
