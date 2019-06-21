package uk.gov.hmcts.ethos.replacement.docmosis.test.util.model.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uk.gov.hmcts.ethos.replacement.docmosis.test.util.model.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JudgementType {

    @JsonProperty("non_hearing_judgment")
    private String nonHearingJudgment;
    @JsonProperty("hearing_number")
    private String hearingNumber;
    @JsonProperty("judgement_type_q")
    private String judgementTypeQ;
    @JsonProperty("liability_default")
    private String liabilityDefault;
    @JsonProperty("judgement_type")
    private String judgementType;
    @JsonProperty("liability_optional")
    private String liabilityOptional;
    @JsonProperty("date_judgment_made")
    private String dateJudgmentMade;
    @JsonProperty("date_judgment_sent")
    private String dateJudgmentSent;
    @JsonProperty("judgement_notes")
    private String judgementNotes;
    @JsonProperty("judgement_outcome_doc")
    private Document judgementOutcomeDoc;
    @JsonProperty("judgement_details")
    private JudgementDetailsType judgementDetails;
    @JsonProperty("Judgement_costs")
    private CostsType judgementCosts;
}