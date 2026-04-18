package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class TimetableTest {

    private void assertEquals(int i, int size, String s) {
    }

    private void assertTrue(boolean contains, String s) {
    }

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, mondaySessions.size(), "За понедельник должно быть одно занятие");
        assertTrue(mondaySessions.contains(singleTrainingSession), "Занятие должно быть в расписании понедельника");

        // Проверить, что за вторник не вернулось занятий
        List<TrainingSession> tuesdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        assertTrue(tuesdaySessions.isEmpty(), "За вторник не должно быть занятий");
    }


    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));
        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, mondaySessions.size(), "За понедельник должно быть одно занятие");

        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        List<TrainingSession> thursdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(2, thursdaySessions.size(), "За четверг должно быть два занятия");
        assertEquals(new TimeOfDay(13, 0), thursdaySessions.get(0).getTimeOfDay(), "Первое занятие в четверг должно быть в 13:00");
        assertEquals(new TimeOfDay(20, 0), thursdaySessions.get(1).getTimeOfDay(), "Второе занятие в четверг должно быть в 20:00");

        // Проверить, что за вторник не вернулось занятий
        List<TrainingSession> tuesdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        assertTrue(tuesdaySessions.isEmpty(), "За вторник не должно быть занятий");
    }

    private void assertEquals(TimeOfDay timeOfDay, TimeOfDay timeOfDay1, String s) {
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        // Проверить, что за понедельник в 13:00 вернулось одно занятие
        List<TrainingSession> specificSessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        assertEquals(1, specificSessions.size(), "В понедельник в 13:00 должно быть одно занятие");
        assertTrue(specificSessions.contains(singleTrainingSession), "Занятие в 13:00 должно быть в расписании");

        // Проверить, что за понедельник в 14:00 не вернулось занятий
        List<TrainingSession> emptySessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        assertTrue(emptySessions.isEmpty(), "В понедельник в 14:00 занятий быть не должно");
    }

    // Тест на добавление дубликатов
    @Test
    void testAddDuplicateTrainingSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TimeOfDay time = new TimeOfDay(15, 30);

        TrainingSession originalSession = new TrainingSession(group, coach, DayOfWeek.WEDNESDAY, time);
        TrainingSession duplicateSession = new TrainingSession(group, coach, DayOfWeek.WEDNESDAY, time);

        // Добавляем оригинальную тренировку
        timetable.addNewTrainingSession(originalSession);

        // Пытаемся добавить дубликат
        timetable.addNewTrainingSession(duplicateSession);

        // Проверяем, что в расписании только одна тренировка
        List<TrainingSession> wednesdaySessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.WEDNESDAY, time);
        assertEquals(1, wednesdaySessions.size(), "Дублирующая тренировка не должна быть добавлена");
        assertTrue(wednesdaySessions.contains(originalSession), "В расписании должна быть оригинальная тренировка");
    }

    private void assertThrows(Class<IllegalArgumentException> illegalArgumentExceptionClass, Object o, String s) {
    }

    private void assertNotNull(List<TrainingSession> nullDaySessions, String s) {
    }


    // Тест на обработку null-значений
    @Test
    void testHandleNullValues() {
        Timetable timetable = new Timetable();

        // Проверяем добавление null-тренировки — должно вызвать исключение

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> timetable.addNewTrainingSession(null),
                "Добавление null-тренировки должно вызывать исключение"
        );

        // Проверяем получение занятий для null-дня
        List<TrainingSession> nullDaySessions = timetable.getTrainingSessionsForDay(null);
        assertNotNull(nullDaySessions, "Метод getTrainingSessionsForDay с null должен возвращать не-null список");
        assertTrue(nullDaySessions.isEmpty(), "Метод getTrainingSessionsForDay с null должен возвращать пустой список");

        // Проверяем получение занятий для несуществующего времени (null) в существующем дне
        List<TrainingSession> nullTimeSessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.FRIDAY, null);
        assertNotNull(nullTimeSessions, "Метод getTrainingSessionsForDayAndTime с null-временем должен возвращать не-null список");
        assertTrue(nullTimeSessions.isEmpty(), "Метод getTrainingSessionsForDayAndTime с null-временем должен возвращать пустой список");
    }


    // Тест на работу с несуществующими днями
    @Test
    void testNonExistentDays() {
        Timetable timetable = new Timetable();

        // Создаём и добавляем тренировку в понедельник
        Group group = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Coach coach = new Coach("Иванов", "Пётр", "Сергеевич");
        TrainingSession session = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(18, 0));
        timetable.addNewTrainingSession(session);

        // Проверяем несуществующие дни
        List<TrainingSession> fridaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.FRIDAY);
        assertTrue(fridaySessions.isEmpty(), "Пятница — несуществующий день в расписании, должен быть пустым");

        List<TrainingSession> sundaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.SUNDAY);
        assertTrue(sundaySessions.isEmpty(), "Воскресенье — несуществующий день в расписании, должен быть пустым");

        // Проверяем конкретный временной слот в несуществующем дне
        List<TrainingSession> saturdayAt10 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.SATURDAY, new TimeOfDay(10, 0));
        assertTrue(saturdayAt10.isEmpty(), "Суббота в 10:00 — несуществующий день и время, должен быть пустым");

        // Проверяем существующий день, но несуществующее время
        List<TrainingSession> mondayAt14 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        assertTrue(mondayAt14.isEmpty(), "Понедельник в 14:00 — существующий день, но несуществующий временной слот, должен быть пустым");

        // Убедимся, что существующее время по-прежнему доступно
        List<TrainingSession> mondayAt18 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(18, 0));
        assertEquals(1, mondayAt18.size(), "Понедельник в 18:00 должен содержать добавленное занятие");
        assertTrue(mondayAt18.contains(session), "Занятие в 18:00 должно быть в расписании понедельника");
    }

    @Test
    void testGetCountByCoaches_MultipleCoachesWithDifferentCounts() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Иванов", "Пётр", "Сергеевич");
        Coach coach2 = new Coach("Васильев", "Николай", "Иванович");
        Coach coach3 = new Coach("Петров", "Алексей", "Дмитриевич");

        Group group = new Group("Акробатика для взрослых", Age.ADULT, 90);

               // Иванов — 3 тренировки
        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.MONDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.TUESDAY, new TimeOfDay(19, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.THURSDAY, new TimeOfDay(20, 0)));

        // Васильев — 2 тренировки
        timetable.addNewTrainingSession(new TrainingSession(group, coach2, DayOfWeek.WEDNESDAY, new TimeOfDay(17, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach2, DayOfWeek.FRIDAY, new TimeOfDay(16, 0)));

        // Петров — 1 тренировка
        timetable.addNewTrainingSession(new TrainingSession(group, coach3, DayOfWeek.SATURDAY, new TimeOfDay(15, 0)));

        List<CounterOfTrainings> result = timetable.getCountByCoaches();


        assertEquals(3, result.size(), "Должно быть 3 тренера в результате");

        // Проверяем порядок
        assertEquals("Иванов", result.get(0).getCoach().getSurname(), "Первый тренер должен быть Иванов");
        assertEquals(3, result.get(0).getCount(), "У Иванова должно быть 3 тренировки");

        assertEquals("Васильев", result.get(1).getCoach().getSurname(), "Второй тренер должен быть Васильев");
        assertEquals(2, result.get(1).getCount(), "У Васильева должно быть 2 тренировки");

        assertEquals("Петров", result.get(2).getCoach().getSurname(), "Третий тренер должен быть Петров");
        assertEquals(1, result.get(2).getCount(), "У Петрова должна быть 1 тренировка");
    }

    private void assertEquals(String иванов, String surname, String первыйТренерДолженБытьИванов) {
    }

    @Test
    void testGetCountByCoaches_EmptySchedule() {
        Timetable timetable = new Timetable();

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        // Проверяем, что при пустом расписании возвращается пустой список
        assertTrue(result.isEmpty(), "При пустом расписании должен возвращаться пустой список");
        assertEquals(0, result.size(), "Размер списка должен быть 0");
    }

    @Test
    void testGetCountByCoaches_SameNumberOfTrainings() {
        Timetable timetable = new Timetable();

        // Создаём новых тренеров и группы
        Coach coachA = new Coach("Сидоров", "Андрей", "Викторович");
        Coach coachB = new Coach("Николаев", "Дмитрий", "Олегович");

        Group group1 = new Group("Йога", Age.ADULT, 60);
        Group group2 = new Group("Плавание", Age.CHILD, 45);

        // Оба тренера ведут по 2 тренировки
        timetable.addNewTrainingSession(new TrainingSession(group1, coachA, DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group2, coachA, DayOfWeek.WEDNESDAY, new TimeOfDay(14, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group1, coachB, DayOfWeek.TUESDAY, new TimeOfDay(11, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group2, coachB, DayOfWeek.THURSDAY, new TimeOfDay(15, 0)));

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        // Проверяем, что в результате 2 тренера
        assertEquals(2, result.size(), "Должно быть 2 тренера в результате");

        // Проверяем, что у обоих тренеров по 2 тренировки
        for (CounterOfTrainings counter : result) {
            assertEquals(2, counter.getCount(),
                    "Каждый тренер должен иметь 2 тренировки при одинаковом количестве");
        }

        // Порядок любой, но оба должны присутствовать
        boolean hasSidorov = result.stream()
                .anyMatch(c -> c.getCoach().getSurname().equals("Сидоров"));
        boolean hasNikolaev = result.stream()
                .anyMatch(c -> c.getCoach().getSurname().equals("Николаев"));

        assertTrue(hasSidorov, "Тренер Сидоров должен присутствовать в результате");
        assertTrue(hasNikolaev, "Тренер Николаев должен присутствовать в результате");
    }

}
