package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("users.txt");
        File users = null;
        for (File file : new File("C:\\images\\").listFiles()) {
            if (name.equals(file.getName())) {
                users = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + users.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(users)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}

