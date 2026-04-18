package ru.yandex.practicum.gym;

import java.util.Objects;

public class TrainingSession {

    //группа
    private Group group;
    //тренер
    private Coach coach;
    //день недели
    private DayOfWeek dayOfWeek;
    //время начала занятия
    private TimeOfDay timeOfDay;

    public TrainingSession(Group group, Coach coach, DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        this.group = group;
        this.coach = coach;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TrainingSession that = (TrainingSession) obj;
        return Objects.equals(group, that.group) &&
                Objects.equals(coach, that.coach) &&
                dayOfWeek == that.dayOfWeek &&
                timeOfDay.equals(that.timeOfDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, coach, dayOfWeek, timeOfDay);
    }

    public Group getGroup() {
        return group;
    }

    public Coach getCoach() {
        return coach;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }
}
