package example.application.repository;

import example.domain.model.attendance.AttendanceOfDay;
import example.domain.model.attendance.Attendances;
import example.domain.model.attendance.MonthlyAttendances;
import example.domain.model.worker.WorkerNumber;
import example.domain.type.date.Date;
import example.domain.type.date.YearMonth;

/**
 * 勤怠リポジトリ
 */
public interface AttendanceRepository {

    void registerAttendance(WorkerNumber workerNumber, AttendanceOfDay attendanceOfDay);

    MonthlyAttendances findMonthly(WorkerNumber workerNumber, YearMonth month);

    Attendances getAttendances(WorkerNumber workerNumber, Date startDate, Date endDate);
}
