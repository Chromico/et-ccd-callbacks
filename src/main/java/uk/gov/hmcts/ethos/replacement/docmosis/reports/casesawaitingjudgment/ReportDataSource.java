package uk.gov.hmcts.ethos.replacement.docmosis.reports.casesawaitingjudgment;

import uk.gov.hmcts.ecm.common.model.ccd.SubmitEvent;

import java.util.List;

public interface ReportDataSource {
    List<SubmitEvent> getData(String caseTypeId);
}
