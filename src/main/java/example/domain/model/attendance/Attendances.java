package example.domain.model.attendance;

import example.domain.type.date.Date;
import example.domain.type.time.HourAndMinute;
import example.domain.type.time.Minute;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 月次勤怠
 */
public class Attendances {
    List<AttendanceOfDay> list;

    public Attendances(List<AttendanceOfDay> list) {
        this.list = list;
    }

    public List<AttendanceOfDay> list() {
        return list;
    }

    public AttendanceOfDay get(Date date) {
        return list.stream().filter(
                w -> w.date().value().equals(date.value())).findFirst().orElseThrow(() -> new RuntimeException());
    }

    public HourAndMinute workTime() {
        return summing((attendanceOfDay) -> attendanceOfDay.workTime());
    }

    public HourAndMinute overTime() {
        return summing((attendanceOfDay) -> attendanceOfDay.overTime());
    }

    public HourAndMinute midnightWorkTime() {
        return summing((attendanceOfDay) -> attendanceOfDay.midnightWorkTime());
    }

    HourAndMinute summing(Function<AttendanceOfDay, HourAndMinute> map) {
        Integer sumMinute = list.stream().map(map).collect(Collectors.summingInt(hourAndMinute -> hourAndMinute.toMinute().value()));
        return HourAndMinute.from(new Minute(sumMinute));
    }
}
