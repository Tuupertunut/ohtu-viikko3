package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String submissionUrl = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String submissionBodyText = Request.Get(submissionUrl).execute().returnContent().asString();

        String courseUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(submissionBodyText);
        System.out.println(courseBodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(submissionBodyText, Submission[].class);
        Course[] courses = mapper.fromJson(courseBodyText, Course[].class);

        System.out.println();
        System.out.println("opiskelijanumero: " + studentNr);

        for (Course co : courses) {
            System.out.println();
            System.out.println(co.getFullName() + " " + co.getTerm() + " " + co.getYear());
            System.out.println();

            int totalDoneExercises = 0;
            int totalHours = 0;

            for (Submission sub : subs) {
                if (sub.getCourse().equals(co.getName())) {

                    int doneExercises = sub.getExercises().size();
                    int hours = sub.getHours();

                    totalDoneExercises += doneExercises;
                    totalHours += hours;

                    System.out.println("viikko " + sub.getWeek());
                    System.out.println("tehtyjä tehtäviä " + doneExercises + "/" + co.getExercises().get(sub.getWeek()) + " aikaa kului " + hours + " tehdyt tehtävät: " + sub.getExercises());
                }
            }

            int totalAvailableExercises = 0;
            for (int ex : co.getExercises()) {
                totalAvailableExercises += ex;
            }

            System.out.println();
            System.out.println("yhteensä: " + totalDoneExercises + "/" + totalAvailableExercises + " tehtävää " + totalHours + " tuntia");
        }
    }
}
