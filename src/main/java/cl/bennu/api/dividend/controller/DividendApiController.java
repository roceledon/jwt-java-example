package cl.bennu.api.dividend.controller;

import cl.bennu.api.dividend.api.DividendApi;
import cl.bennu.api.dividend.model.Dividend;
import cl.bennu.api.dividend.model.ErrorResponse;
import cl.bennu.api.dividend.model.Status;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@CrossOrigin
@RestController
public class DividendApiController implements DividendApi{
    public ResponseEntity<Status> getStatus(@PathVariable String mutualCode) {
        Status status = new Status();
        status.setCode(200L);
        status.setMessage("message success");
        status.setQuota(6L);
        status.setStatus(Boolean.TRUE);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getDividend(@PathVariable String mutualCode) {
        ErrorResponse error = new ErrorResponse();

        try {
            if (mutualCode != null) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
                String pdf = resourceBundle.getString("pdf.base64");

                Dividend dividend = new Dividend();
                dividend.setCode(200L);
                dividend.setMessage("dividend success");
                dividend.setQuota(6L);
                dividend.setMutualCode(mutualCode);
                dividend.setDayToPay(new Date());
                dividend.setPdf(pdf);
                return new ResponseEntity<>(dividend, HttpStatus.OK);
            }

            error.setCode(404L);
            error.setMessage("not found");

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            error.setCode(401L);
            error.setMessage("unauthorized");

            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}
