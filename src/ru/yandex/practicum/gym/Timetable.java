package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable =
            new HashMap<>(); // по картинке в объяснении


    public void addNewTrainingSession(TrainingSession trainingSession) {

        if (trainingSession == null) {
            throw new IllegalArgumentException("Поле тренировки не может быть пустым");
        }

        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.computeIfAbsent(
                day, k -> new TreeMap<>());

        List<TrainingSession> sessionsAtTime = dayMap.computeIfAbsent(
                time, k -> new ArrayList<>());

        if (!sessionsAtTime.contains(trainingSession)) {
            sessionsAtTime.add(trainingSession);
        }
    }


    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(dayOfWeek);

        if (dayMap == null) {
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
            System.out.println("Нет тренировок в указанное время");
            return Collections.emptyList();
        }

        List<TrainingSession> sessions = dayMap.get(timeOfDay);
        if (sessions == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(sessions);
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, CounterOfTrainings> coachCounterMap = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessionsAtTime : dayMap.values()) {
                for (TrainingSession session : sessionsAtTime) {
                    Coach coach = session.getCoach();
                    if (!coachCounterMap.containsKey(coach)) {
                        coachCounterMap.put(coach, new CounterOfTrainings(coach, 1));
                    } else {
                        coachCounterMap.get(coach).increment();
                    }
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>(coachCounterMap.values());
        result.sort((c1, c2) -> Integer.compare(c2.getCount(), c1.getCount()));

        return result;
    }

}
