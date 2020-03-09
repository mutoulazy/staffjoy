package std.staffjoy.company.service;

import org.springframework.stereotype.Service;

import java.util.TimeZone;
import std.staffjoy.company.dto.TimeZoneList;

@Service
public class TimeZoneService {

  public TimeZoneList listTimeZones() {
    TimeZoneList timeZoneList = TimeZoneList.builder().build();
    for (String id : TimeZone.getAvailableIDs()) {
      timeZoneList.getTimezones().add(id);
    }
    return timeZoneList;
  }
}
