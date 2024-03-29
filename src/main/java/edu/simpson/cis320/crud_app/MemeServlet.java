package edu.simpson.cis320.crud_app;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "memeServlet", value = "/meme")
public class MemeServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(MemeServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        log.log(Level.INFO, "Meme Servlet");

        ServletContext context = getServletContext();
        InputStream imageStream = context.getResourceAsStream("WEB-INF/classes/dog_glasses.jpg");
        BufferedImage image = ImageIO.read(imageStream);

        // Get Graphics Context
        Graphics g = image.getGraphics();

        // Set Font
        String fontName = "Comic Sans";
        int fontSize = 50;
        int fontStyle = Font.ITALIC;
        Font font = new Font(fontName, fontStyle, fontSize);
        g.setFont(font);

        String message = request.getParameter("message");
        if (message == null) {
            message = "no meme";
        }

        // Select Color
        Color myColor = new Color(0, 0, 0);
        g.setColor(myColor);

        // Draw the String
        g.drawString(message, 100, 100);

        // Dispose of the Pen
        g.dispose();

        // Write Out the Image
        response.setContentType("image/jpg");
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPG", out);
    }

}