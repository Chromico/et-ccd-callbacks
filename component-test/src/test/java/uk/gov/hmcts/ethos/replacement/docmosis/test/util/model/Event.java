package uk.gov.hmcts.ethos.replacement.docmosis.test.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    @JsonProperty("id")
    private String eventId;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("description")
    private String description;

}