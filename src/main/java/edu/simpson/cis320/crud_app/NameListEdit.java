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

@WebServlet(name = "NameListEdit", value = "/api/name_list_edit")


public class NameListEdit extends HttpServlet {
    private final static Logger log = Logger.getLogger(NameListEdit.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.log(Level.INFO, "doPost for NameListEdit");

        response.setContentType("text/JSON");
        PrintWriter out = response.getWriter();

        java.io.BufferedReader in = request.getReader();
        String requestString = new String();
        for (String line; (line = in.readLine()) != null; requestString += line);

        log.log(Level.INFO, requestString);

        Jsonb jsonb = JsonbBuilder.create();
        Person personObject = jsonb.fromJson(requestString, Person.class);
        PersonDAO.addPerson(personObject);

    }

}



