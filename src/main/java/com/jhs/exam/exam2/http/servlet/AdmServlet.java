package com.jhs.exam.exam2.http.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.http.controller.Controller;
import com.jhs.mysqliutil.MysqlUtil;

@WebServlet("/adm/*")
public class AdmServlet extends DispatcherServlet {
	
}
