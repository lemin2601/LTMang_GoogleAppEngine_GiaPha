<%@page import="controller.LoginServlet"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>  
                    </button>
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/about.html"><img src="img/profile.jpg" alt="LeeMin" style="width: 25px" class="img-circle"></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="<%=request.getContextPath()%>/about.html">About</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">...</a></li>
                                <li><a href="#">...</a></li>
                                <li><a href="#">...</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <%
                            User user = (User) session.getAttribute(LoginServlet.USER_SESSION);
                            if (user == null) {
                        %>
                        <li><a href="<%=request.getContextPath()%>/register.jsp"><span
                                    class="glyphicon glyphicon-log-in"></span> Register</a></li>
                        <li><a href="<%=request.getContextPath()%>/login.jsp"><span
                                    class="glyphicon glyphicon-log-in"></span> Login</a></li>
                                <%
                                } else {
                                %>
                        <li><a href="#" style="width: auto;"> Welcome <b><%=user.getUsername()%></b></a></li>
                        <li><a href="<%=request.getContextPath()%>/User?command=logout" style="width: auto;"><span
                                    class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                                <%
                                    }
                                %>
                    </ul>
                </div>
            </div>
        </nav>
       
    </body>
</html>