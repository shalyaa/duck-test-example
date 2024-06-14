package autotests.payloads;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Duck {

    @JsonProperty("color")
    private String color;

    @JsonProperty("height")
    private double height;

    @JsonProperty("material")
    private String material;

    @JsonProperty("sound")
    private String sound;

    @JsonProperty
    private String id;

    @JsonProperty("wingsState")
    private WingState wingsState;


}
