package uk.gov.hmcts.ethos.replacement.docmosis.test.util.model.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DepositType {

    @JsonProperty("Deposit_amount")
    private String depositAmount;
    @JsonProperty("deposit_requested_by")
    private String depositRequestedBy;
    @JsonProperty("deposit_covers")
    private String depositCovers;
    @JsonProperty("deposit_order_sent")
    private String depositOrderSent;
    @JsonProperty("deposit_due")
    private String depositDue;
    @JsonProperty("deposit_received")
    private String depositReceived;
    @JsonProperty("deposit_time_ext")
    private String depositTimeExt;
    @JsonProperty("deposit_time_ext_due")
    private String depositTimeExtDue;
    @JsonProperty("deposit_refund")
    private String depositRefund;
    @JsonProperty("deposit_refund_date")
    private String depositRefundDate;
    @JsonProperty("deposit_jurisdiction_codes")
    private String depositJurisdictionCodes;
}