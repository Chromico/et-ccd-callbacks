package uk.gov.hmcts.ethos.replacement.docmosis.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CcdClientConfigTest {

    @InjectMocks
    private CcdClientConfig ccdClientConfig;
    @Mock
    private AuthTokenGenerator authTokenGenerator;

    @Test
    public void buildStartCaseCreationUrl() {
        String uri = ccdClientConfig.buildStartCaseCreationUrl("1123", "TRIBUNALS", "TRIB_03");
        assertEquals("null/caseworkers/1123/jurisdictions/TRIBUNALS/case-types/TRIB_03/event-triggers/" +
                "initiateCase/token?ignore-warning=true", uri);
    }

    @Test
    public void buildSubmitCaseCreationUrl() {
        String uri = ccdClientConfig.buildSubmitCaseCreationUrl("1123", "TRIBUNALS", "TRIB_03");
        assertEquals("null/caseworkers/1123/jurisdictions/TRIBUNALS/case-types/TRIB_03/cases", uri);
    }

    @Test
    public void buildRetrieveCaseUrl() {
        String uri = ccdClientConfig.buildRetrieveCaseUrl("1123", "TRIBUNALS", "TRIB_03", "1222222");
        assertEquals("null/caseworkers/1123/jurisdictions/TRIBUNALS/case-types/TRIB_03/cases/1222222", uri);
    }

    @Test
    public void buildHeaders() {
        when(authTokenGenerator.generate()).thenReturn("authString");
        HttpHeaders httpHeaders = ccdClientConfig.buildHeaders("authString");
        assertEquals("[Authorization:\"authString\", ServiceAuthorization:\"authString\", " +
                "Content-Type:\"application/json;charset=UTF-8\"]", httpHeaders.toString());
    }
}