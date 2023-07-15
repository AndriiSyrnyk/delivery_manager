package dodomu.deliverymanager.schedule;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.employee_schedule.EmployeeSchedule;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ScheduleUtil {
    public static SortedMap<Date, List<ScheduleDTO>> createSchedule(
            List<Date> datesByYearMonth,
            List<EmployeeSchedule> employeeScheduleList,
            List<Schedule> scheduleList) {
        SortedMap<Date, List<ScheduleDTO>> scheduleMap = new TreeMap<>();

        for (Date date : datesByYearMonth) {
            List<ScheduleDTO> schedulesByDate = new ArrayList<>();

            for (EmployeeSchedule employeeSchedule : employeeScheduleList) {
                ScheduleDTO schedule = getScheduleByDateAndEmployee(date, employeeSchedule.getEmployee(), scheduleList);
                schedulesByDate.add(schedule);
            }

            scheduleMap.put(date, schedulesByDate);
        }

        return scheduleMap;
    }

    private static ScheduleDTO getScheduleByDateAndEmployee(Date date, Employee employee, List<Schedule> scheduleList) {
        final ScheduleId scheduleId = new ScheduleId(employee.getId(), date);
        ScheduleDTO scheduleToFind = new ScheduleDTO(scheduleId, employee, date);

        for (Schedule schedule : scheduleList) {
            if (schedule.getEmployee().equals(employee) &&
                schedule.getDate().equals(date)) {
                scheduleToFind = new ScheduleDTO(schedule);
                final Time startTime = schedule.getStartTime();
                final Time endTime = schedule.getEndTime();
                scheduleToFind.setStartTime(timeToStringTime(startTime));
                scheduleToFind.setEndTime(timeToStringTime(endTime));
                break;
            }
        }

        return scheduleToFind;
    }

    public static Schedule scheduleDTOParametersToSchedule(Employee employee, Date dateParam, String startTimeString, String endTimeString, Integer amountPaid) {
        Schedule schedule = new Schedule(new ScheduleId(employee.getId(), dateParam));
        schedule.setDate(dateParam);
        schedule.setEmployee(employee);
        schedule.setStartTime(stringTimeToTime(startTimeString));
        schedule.setEndTime(stringTimeToTime(endTimeString));
        schedule.setAmountPaid(amountPaid == null ? 0 : amountPaid);
        return schedule;
    }

    private static String timeToStringTime(Time time) {
        return time.toString().substring(0, 5);
    }

    private static Time stringTimeToTime(String timeString) {
        DateFormat timeFormat = new SimpleDateFormat("hh:mm");
        java.util.Date parsedDate = new java.util.Date();
        try {
            parsedDate = timeFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Time(parsedDate.getTime());
    }
}
