package uk.gov.hmcts.ethos.replacement.docmosis.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.ethos.replacement.docmosis.config.TornadoConfiguration;
import uk.gov.hmcts.ethos.replacement.docmosis.helpers.Helper;
import uk.gov.hmcts.ethos.replacement.docmosis.model.ccd.CaseDetails;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static java.net.HttpURLConnection.HTTP_OK;
import static uk.gov.hmcts.ethos.replacement.docmosis.helpers.Helper.OUTPUT_FILE_NAME;

@Service("tornadoService")
@RequiredArgsConstructor
public class TornadoService {

    private final TornadoConfiguration tornadoConfiguration;
    private final DocumentManagementService documentManagementService;
    private static final Logger log = LoggerFactory.getLogger(TornadoService.class);

    String documentGeneration(String authToken, CaseDetails caseDetails) throws IOException {
        HttpURLConnection conn = null;
        String filePath = "";
        try {
            conn = createConnection();
            log.info("Connected");
            buildInstruction(conn, caseDetails);
            int status = conn.getResponseCode();
            if (status == HTTP_OK) {
                filePath = createDocument(authToken, conn);
            } else {
                log.error("Our call failed: status = " + status);
                log.error("message:" + conn.getResponseMessage());
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String msg;
                while ((msg = errorReader.readLine()) != null) {
                    log.error(msg);
                }
            }
        } catch (ConnectException e) {
            log.error("Unable to connect to Docmosis: " + e.getMessage());
            log.error("If you have a proxy, you will need the Proxy aware example code.");
            System.exit(2);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return filePath;
    }

    private HttpURLConnection createConnection() throws IOException {
        String tornadoURL = tornadoConfiguration.getUrl();
        log.info("TORNADO URL: " + tornadoURL);
        HttpURLConnection conn = (HttpURLConnection) new URL(tornadoURL).openConnection();
        log.info("Connecting [directly] to " + tornadoURL);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.connect();
        return conn;
    }

    private void buildInstruction(HttpURLConnection conn, CaseDetails caseDetails) throws IOException {
        StringBuilder sb = Helper.buildDocumentContent(caseDetails, tornadoConfiguration.getAccessKey());
        log.info("Sending request: " + sb.toString());
        // send the instruction in UTF-8 encoding so that most character sets are available
        OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
        os.write(sb.toString());
        os.flush();
    }

    private String createDocument(String authToken, HttpURLConnection conn) throws IOException{
        byte[] buff = new byte[1000];
        int bytesRead;
        File file = new File(OUTPUT_FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            while ((bytesRead = conn.getInputStream().read(buff, 0, buff.length)) != -1) {
                fos.write(buff, 0, bytesRead);
            }
        }
        log.info("File created: " + file.getAbsolutePath());

        URI documentSelfPath = documentManagementService.uploadDocument(authToken, file);
        String markupURL = documentManagementService.generateMarkupDocument(documentSelfPath);
        log.info("MarkupURL: " + markupURL);
        return markupURL;
    }

}
