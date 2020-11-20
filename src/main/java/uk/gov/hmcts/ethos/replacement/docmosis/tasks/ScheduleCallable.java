package uk.gov.hmcts.ethos.replacement.docmosis.tasks;

import lombok.extern.slf4j.Slf4j;
import uk.gov.hmcts.ecm.common.model.ccd.SubmitEvent;
import uk.gov.hmcts.ecm.common.model.helper.SchedulePayload;
import uk.gov.hmcts.ethos.replacement.docmosis.helpers.MultiplesScheduleHelper;
import uk.gov.hmcts.ethos.replacement.docmosis.service.excel.SingleCasesReadingService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class ScheduleCallable implements Callable<List<SchedulePayload>> {

    private SingleCasesReadingService singleCasesReadingService;
    private String userToken;
    private String caseTypeId;
    private List<String> partitionCaseIds;

    public ScheduleCallable(SingleCasesReadingService singleCasesReadingService, String userToken, String caseTypeId, List<String> partitionCaseIds) {
        this.singleCasesReadingService = singleCasesReadingService;
        this.userToken = userToken;
        this.caseTypeId = caseTypeId;
        this.partitionCaseIds = partitionCaseIds;
    }

    @Override
    public List<SchedulePayload> call() {

        List<SchedulePayload> schedulePayloads = new ArrayList<>();

        List<SubmitEvent> submitEvents = singleCasesReadingService.retrieveSingleCases(userToken,
                caseTypeId, partitionCaseIds);

        for (SubmitEvent submitEvent : submitEvents) {

            schedulePayloads.add(MultiplesScheduleHelper.getSchedulePayloadFromSubmitEvent(submitEvent));

        }

        return schedulePayloads;

    }

}
