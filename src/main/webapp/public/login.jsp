<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>
	<%
		//check cookie
		String username ="",password ="";
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();// Get an array of Cookies associated with the this domain
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if(cookie.getName().equals("username")) username = cookie.getValue();
				if(cookie.getName().equals("password")) password = cookie.getValue();
			}
		}
	%>
	<div class="container-fluid " style="height: 90vh;">
		<div class="row" align="center" style="height: 30vh;"></div>
		
		<div class="row" align="center">
			<div class="col-xs-4 col-xs-offset-4">
				<form id="login-form" >
					<div class="modal-body ">
						<div id="div-login-msg">
							<div id="icon-login-msg"
								class="glyphicon glyphicon-chevron-right"></div>
							<span id="text-login-msg">Type your username and password.</span>
						</div>
						<input id="login_username" class="form-control" type="text"
							placeholder="Username (type ERROR for error effect)" value ="<%=username%>" required>
						<input id="login_password" class="form-control" type="password"
							placeholder="Password" value ="<%=password%>" required>
						<div class="checkbox">
							<label> <input name="savepasss" type="checkbox" checked> Remember me
							</label>
						</div>
					</div>
					<div class="modal-footer">
						<div>
							<button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
						</div>
						<div>
							<button id="login_lost_btn" type="button" class="btn btn-link">Lost
								Password?</button>
							<button id="login_register_btn" type="button"
								class="btn btn-link">Register</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>

	<script type="text/javascript" src="../myjs/login.js"></script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
