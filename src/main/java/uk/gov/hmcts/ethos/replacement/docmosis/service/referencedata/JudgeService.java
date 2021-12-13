package uk.gov.hmcts.ethos.replacement.docmosis.service.referencedata;

import uk.gov.hmcts.ecm.common.model.bulk.types.DynamicValueType;
import uk.gov.hmcts.ecm.common.model.helper.TribunalOffice;

import java.util.List;

public interface JudgeService {
    List<DynamicValueType> getJudges(TribunalOffice tribunalOffice);
}
