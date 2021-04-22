package edu.simpson.cis320.crud_app;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "NameListEdit", value = "/api/name_list_edit")


public class NameListEdit extends HttpServlet {
    private final static Logger log = Logger.getLogger(NameListEdit.class.getName());
    private Pattern firstValidationPattern;
    private Pattern lastValidationPattern;
    private Pattern emailValidationPattern;
    private Pattern phoneValidationPattern;
    private Pattern birthdayValidationPattern;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        firstValidationPattern = Pattern.compile("^[A-Za-z]{1,10}$");
        lastValidationPattern = Pattern.compile("^[A-Za-z]{1,10}$");
        emailValidationPattern = Pattern.compile("^.+@.+$");
        phoneValidationPattern = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");
        birthdayValidationPattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");

        log.log(Level.INFO, "doPost for NameListEdit");

        response.setContentType("text/JSON");
        PrintWriter out = response.getWriter();

        java.io.BufferedReader in = request.getReader();
        String requestString = new String();
        for (String line; (line = in.readLine()) != null; requestString += line);

        log.log(Level.INFO, requestString);

        Jsonb jsonb = JsonbBuilder.create();
        Person personObject = jsonb.fromJson(requestString, Person.class);
        // Now create matcher object.
        Matcher m = firstValidationPattern.matcher(personObject.getFirst());
        if (!m.find( )) {
            out.println("error");
        }
        m = lastValidationPattern.matcher(personObject.getLast());
        if (!m.find( )) {
            out.println("error");
        }
        m = emailValidationPattern.matcher(personObject.getEmail());
        if (!m.find( )) {
            out.println("error");
        }
        m = phoneValidationPattern.matcher(personObject.getPhone());
        if (!m.find( )) {
            out.println("error");
        }
        m = birthdayValidationPattern.matcher(personObject.getBirthday());
        if (!m.find( )) {
            out.println("error");
        }

        if (personObject.getId() == 0) {
            PersonDAO.addPerson(personObject);
        }

        else {
            PersonDAO.editPerson(personObject);
        }

        out.print("{\"message\": \"success\"}");
    }

}



