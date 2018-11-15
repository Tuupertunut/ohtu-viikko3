package ohtu;

import java.util.List;

public class Submission {

    private int week;
    private int hours;
    private List<Integer> exercises;
    private String course;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return course + " viikko " + week + ", tehtyjä tehtäviä yhteensä " + exercises.size() + ", aikaa kului " + hours + ", tehdyt tehtävät: " + exercises;
    }

}
