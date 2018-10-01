package cl.bennu.api.dividend.api;

import cl.bennu.api.dividend.model.Status;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "${route.dividend.path}")
public interface DividendApi {
    @RequestMapping(value = "${route.dividend.status}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<Status> getStatus();
}