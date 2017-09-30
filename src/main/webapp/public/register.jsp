<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>
	<div class="container-fluid " style="height: 90vh;">
		<div class="row" align="center" style="height: 30vh;"></div>

		<div class="row" align="center">
			<div class="col-xs-4 col-xs-offset-4">
				<form id="register-form" >
					<div class="modal-body">
						<div id="div-register-msg">
							<div id="icon-register-msg"
								class="glyphicon glyphicon-chevron-right"></div>
							<span id="text-register-msg">Register an account.</span>
						</div>
						<input name="username" id="register_username" class="form-control"
							type="text" placeholder="Username (type ERROR for error effect)"
							required> <input name="email" id="register_email"
							class="form-control" type="text" placeholder="E-Mail" required>
						<input name = "password" id="register_password" class="form-control" type="password"
							placeholder="Password" required>
					</div>
					<div class="modal-footer">
						<div>
							<button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
						</div>
						<div>
							<button id="register_login_btn" type="button"
								class="btn btn-link">Log In</button>
							<button id="register_lost_btn" type="button" class="btn btn-link">Lost
								Password?</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>

	<script type="text/javascript" src="../myjs/register.js"></script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
