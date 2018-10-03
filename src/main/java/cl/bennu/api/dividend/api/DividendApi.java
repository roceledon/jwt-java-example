package cl.bennu.api.dividend.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface DividendApi {
    @RequestMapping(value = "${route.path.status}"+"/{mutualCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<?> getStatus(@PathVariable String mutualCode);

    @RequestMapping(value = "${route.path.dividend}"+"/{mutualCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<?> getDividend(@PathVariable String mutualCode);
}