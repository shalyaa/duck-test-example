package autotests.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class Order {

    @JsonProperty
    private int id;

    @JsonProperty
    private int petId;

    @JsonProperty
    private int quantity;

    @JsonProperty
    private String shipDate;

    @JsonProperty
    private String status;

    @JsonProperty
    private Boolean complete;
}
