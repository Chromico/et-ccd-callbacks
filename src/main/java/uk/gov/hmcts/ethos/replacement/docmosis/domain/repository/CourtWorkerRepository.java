package uk.gov.hmcts.ethos.replacement.docmosis.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.hmcts.ecm.common.model.bulk.types.DynamicValueType;
import uk.gov.hmcts.ethos.replacement.docmosis.data.CourtWorker;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourtWorkerRepository extends JpaRepository<CourtWorker, UUID> {
    List<DynamicValueType> getCourtWorkersByOffice(String office, String lookUpId);
}

