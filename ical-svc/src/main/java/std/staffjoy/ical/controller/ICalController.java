package std.staffjoy.ical.controller;

import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import std.staffjoy.ical.model.Cal;
import std.staffjoy.ical.service.ICalService;

@Controller
public class ICalController {

  @Autowired
  private ICalService iCalService;

  @GetMapping(value = "/{user_id}.ics")
  public @ResponseBody
  HttpEntity<byte[]> getCalByUserId(@PathVariable(value = "user_id") String userId) {
    Cal cal = iCalService.getCalByUserId(userId);

    byte[] calBytes = cal.build().getBytes();

    HttpHeaders header = new HttpHeaders();
    header.setContentType(new MediaType("application", "calendar", Charset.forName("UTF-8")));
    header.set("Content-Disposition", "attachment; filename=" + userId + ".ics");
    header.setContentLength(calBytes.length);
    return new HttpEntity<>(calBytes, header);
  }
}

