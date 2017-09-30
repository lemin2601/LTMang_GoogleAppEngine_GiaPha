<%@page import="model.User"%>
<%@page import="model.User"%>
<%@page import="controller.LoginServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
<!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->

<title>Welcome to login system</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" >-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="../library/bootstrap/css/w3.css" rel="stylesheet">
<link href="../library/bootstrap/css/bootstrap-select.min.css"
	rel="stylesheet">
<!--css danh cho select-->
<link rel="stylesheet"
	href="../library/bootstrap/css/bootstrap-select.css">
<link rel="stylesheet"
	href="https://cdn.rawgit.com/tonystar/bootstrap-float-label/v3.0.0/dist/bootstrap-float-label.css">

<link
	href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css"
	rel="stylesheet">
<!--dành cho tree & zoom-->
<script src="../library/zoom/libs/jquery.js"></script>
<script src="../library/zoom/dist/jquery.panzoom.js"></script>
<script src="../library/zoom/libs/jquery.mousewheel.js"></script>
<link href="../library/mystyle/tree.css" rel="stylesheet">
<!--css login & register form-->
<link href="../library/mystyle/login.css" rel="stylesheet">
<!--css cho form table gia pha-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
	rel='stylesheet' type='text/css'>
<!-- bootstrap datepicker -->
<link rel="stylesheet"
	href="../library/plugins/datepicker/datepicker3.css">
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute(LoginServlet.USER_SESSION);		
	%>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/about.html"><img
					src="<%=request.getContextPath()%>/img/logo.jpg" alt="LeeMin"
					style="width: 25px"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="<%=request.getContextPath()%>/about.html">About</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Menu<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">View by Table</a></li>
							<li><a href="#">View by Grid</a></li>
						</ul></li>
					<!--<li><a data-toggle="collapse" data-target="#demo" ><span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span> Togle Menu</a></li>-->
				</ul>

				<!-- 				<form class="navbar-form navbar-right"> -->
				<!-- 					<div class="input-group"> -->
				<!-- 						<input type="text" class="form-control" placeholder="Search"> -->
				<!-- 						<div class="input-group-btn"> -->
				<!-- 							<button class="btn btn-default" type="submit"> -->
				<!-- 								<i class="glyphicon glyphicon-search"></i> -->
				<!-- 							</button> -->
				<!-- 						</div> -->
				<!-- 					</div> -->
				<!-- 				</form> -->
				<ul class="nav navbar-nav navbar-right">
					<%
						if (user != null) {
					%>
					<li><a href="#" style="width: auto;"> Welcome <b><%=user.getEmail()%></b></a>
					</li>
					<li><a
						href="<%=request.getContextPath()%>/LogoutServlet"
						style="width: auto;"><span class="glyphicon glyphicon-log-in"></span>
							Logout</a></li>
					<%
						} else {
					%>
					<li><a>Chào Mừng Bạn Đến Với Sơ Đồ Gia Phả Online</a></li>
					<li><a href="login.jsp" role="button"> <span
							class="glyphicon glyphicon-log-in"></span> Login
					</a></li>
					<li><a href="register.jsp" role="button"> <span
							class="glyphicon glyphicon-log-in"></span> Register
					</a></li>
					<%
						}
						if (user != null && user.getRoles() > 2) {
					%>
					<li><a href="<%=request.getContextPath()%>/UserManagerServlet"
						style="width: auto;"><span class="glyphicon glyphicon-log-in"></span>
							Go to Admin page</a></li>


					<%
						}
					%>



				</ul>
			</div>
		</div>
	</nav>
</body>
</html>