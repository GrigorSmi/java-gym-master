package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable =
            new HashMap<>(); // по картинке в объяснении


    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.computeIfAbsent(
                day, k -> new TreeMap<>());

        List<TrainingSession> sessionsAtTime = dayMap.computeIfAbsent(
                time, k -> new ArrayList<>());

        sessionsAtTime.add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(dayOfWeek);

        if (dayMap == null) {
            System.out.println("Нет тренировок в " + dayOfWeek);
            return Collections.emptyList();
        }


        List<TrainingSession> allTrainingsOfDay = new ArrayList<>();
        for (TimeOfDay timeOfDay : dayMap.navigableKeySet()) {
            List<TrainingSession> sessions = dayMap.get(timeOfDay);
            allTrainingsOfDay.addAll(sessions);
        }
        return allTrainingsOfDay;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(dayOfWeek);

        if (dayMap == null) {
            System.out.println("Нет тренировок");
            return Collections.emptyList();
        }

        List<TrainingSession> sessions = dayMap.get(timeOfDay);
        if (sessions == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(sessions);
    }
}
