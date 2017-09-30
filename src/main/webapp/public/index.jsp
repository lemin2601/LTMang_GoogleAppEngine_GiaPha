<%@page import="model.User"%>
<%@page import="controller.LoginServlet"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html>
<%
	User user = (User) request.getSession().getAttribute(LoginServlet.USER_SESSION);
	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>
<jsp:include page="header.jsp"></jsp:include>
<body>
	<!--Menu here Xem chi tiết thêm mới, sửa, tìm kiếm thêm mới-->
	<div class="container-fluid ">
		<div id="demo" class="collapse1">
			<div class="container-fluid ">
				<div class="col-sm-12">
					<div class="col-sm-1">
						<button id="btn-show-form-create-genealogy" type="button"
							class="btn btn-block btn-success">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  Genealogy
						</button>
					</div>
					<div class="col-sm-1">
						<button id="btn-show-form-edit-genealogy" type="button"
							class="btn btn-block btn-warning">
							<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>  Genealogy
						</button>
					</div>
					<div class="col-sm-1">
						<button id="btn-show-form-delete-genealogy" type="button"
							class="btn btn-block btn-danger">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>  Genealogy
						</button>
					</div>
					<div class="col-sm-2">
						<div class="form-group has-float-label">
							<select class="form-control selectpicker" data-live-search="true"
								data-width="100%" id="guide-genealogy">

							</select> <label for="guide-genealogy">Genealogy name</label>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group has-float-label">
							<select class="form-control selectpicker" data-live-search="true"
								data-width="100%" id="guide-name">

							</select> <label for="guide-name">People name</label>
						</div>

					</div>
					<div class="col-sm-1">
						<div class="form-group has-float-label">
							<select class="form-control selectpicker" data-live-search="true"
								data-width="100%" id="guide-deep-genealogy">
								<option>2</option>
								<option selected>3</option>
								<option>4</option>
								<option>5</option>
								<option>6</option>
								<option>7</option>
								<option>8</option>
								<option>9</option>
								<option>10</option>
							</select> <label for="guide-deep-genealogy">deep view</label>
						</div>
					</div>

					<div class="col-sm-2">
						<button id="btn-xem-gia-pha" type="button"
							class="btn btn-info btn-block navbar-right">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span> View
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--title des & zoom + menu button-->
	<div class="container-fluid ">
		<div class="col-sm-2">
			<!-- 			<button type="button" class="btn btn-default" data-toggle="collapse" -->
			<!-- 				data-target="#demo"> -->
			<!-- 				<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> -->
			<!-- 			</button> -->
		</div>
		<div class="col-sm-8">
			<div class="row">
				<input type="hidden" id="view-genealogy-id" value="">
				<h1 id="view-genealogy-name" class="text-center">Please insert new genealogy</h1>
				<p id="view-genealogy-description" class="text-right"></p>
				<div id="show-btn-insert-first-people"></div>
			</div>
		</div>
		<div class="col-sm-2">
			<button id="btn-zoom-plus" type="button" class="btn navbar-right">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			</button>
			<button id="btn-zoom-refresh" type="button" class="btn navbar-right">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
			</button>
			<button id="btn-zoom-minus" type="button" class="btn navbar-right">
				<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
			</button>
		</div>
	</div>
	<!--khu vuc ve cay gia pha-->
	<section id="focal">
		<div id="tree-parent" class="parent"
			style="height: 85vh; width: 100%;">
			<div class="panzoom">
				<div id="tree" class="tree"
					style="zoom: 100%; height: 100vh; width: 1000%;"></div>
			</div>
		</div>
		<script>
                    (function () {
                        var $section = $('#focal');
                        var $panzoom = $section.find('.panzoom').panzoom({
                            startTransform: 'scale(0.75)',
                            which: 1,
                            minScale: 0.01,
                            maxScale: 6,
                            duration: 200,
                            $zoomIn: $('#btn-zoom-plus'),
                            $zoomOut: $('#btn-zoom-minus'),
                            $zoomRange: $(),
                            $reset: $('#btn-zoom-refresh'),
                            //$set: $('#btn-xem-gia-pha'),
                        }
                        );
                        $panzoom.parent().on('mousewheel.focal', function (e) {
                            e.preventDefault();
                            var delta = e.delta || e.originalEvent.wheelDelta;
                            var zoomOut = delta ? delta < 0 : e.originalEvent.deltaY > 0;
                            $panzoom.panzoom('zoom', zoomOut, {
                                increment: 0.1,
                                focal: e
                            });
                        });
                    })();
                </script>
	</section>
	<!-- form-create-genealogy -->
	<div class="modal fade" id="form-create-genealogy" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Create new genealogy</h4>
				</div>
				<div class="modal-body">
					<form data-toggle="validator" id="form-create-genealogy-submit"
						action="../AddGenealogyServlet" method="post">
						<input type="hidden" name="command" value="create_genealogy">
						<input type="hidden" name="id" class="create-id">

						<div class="form-group">
							<label>Name</label> <input name="name" type="text"
								class="form-control" placeholder="Name genealogy"
								data-error="Please enter name" required>
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label>Description:</label>
							<textarea name="description" class="form-control"
								placeholder="Description"></textarea>
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<div id='message-form-create-genealogy'
								class="col-sm-offset-2 col-sm-10"></div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-block btn-success ">Create</button>
							<button data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- form-edit-genealogy -->
	<div class="modal fade" id="form-edit-genealogy" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit my genealogy</h4>
				</div>
				<div class="modal-body">
					<form data-toggle="validator" id="form-edit-genealogy-submit"
						action="../EditGenealogyServlet" method="post">
						<input type="hidden" name="command" value="edit_genealogy">
						<input type="hidden" name="id" class="edit-id">
						<div class="form-group">
							<label>Name</label> <input name="name" type="text"
								class="form-control" placeholder="Name genealogy"
								data-error="Please enter name" required>
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label>Description:</label>
							<textarea name="description" class="form-control"
								placeholder="Description"></textarea>
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<div id='message-form-edit-genealogy'
								class="col-sm-offset-2 col-sm-10"></div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-block btn-success ">Update</button>
							<button data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- form-delete-genealogy -->
	<div class="modal fade" id="form-delete-genealogy" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Delete my genealogy</h4>
				</div>
				<div class="modal-body">
					<form data-toggle="validator" id="form-delete-genealogy-submit"
						action="../DeleteGenealogySeverlet" method="post">
						<input type="hidden" name="command" value="delete_genealogy">
						<input type="hidden" name="id" class="delete-id">
						<div class="form-group">
							<label>Name</label> <input name="name" type="text"
								class="form-control" placeholder="Name genealogy"
								data-error="Please enter name" readonly>
							<div class="help-block with-errors"></div>
						</div>

						<div class="form-group">
							<div id='message-form-delete-genealogy'
								class="col-sm-offset-2 col-sm-10"></div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-block btn-success ">Delete</button>
							<button data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>



	<!-- view form people Modal -->
	<div class="modal fade" id="form-view-people" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">View people</h4>
				</div>
				<div class="modal-body">
					<form data-toggle="validator" id="form-view-people-submit">
						<input type="hidden" name="command" value="view"> <input
							type="hidden" name="id" class="view-id">

						<div class="form-group">
							<div class="col-xs-7">
								<div class="form-group">
									<label>First name</label> <input name="firstname" type="text"
										class="form-control" placeholder="First name" readonly>
								</div>
								<div class="form-group">
									<label>Last name</label> <input name="lastname" type="text"
										class="form-control" placeholder="Last name" readonly>
								</div>
							</div>
							<div class="col-xs-5">
								<div class="form-group">
									<img src="../img/congai.jpg" id='img-upload' class="w3-circle"
										style="width: 142px; height: 142px;">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label>Alias</label> <input name="alias" type="text"
									class="form-control" placeholder="Alias" readonly>
							</div>
							<div class="col-xs-6">
								<label>Sex</label> <select name="sex" class="form-control"
									data-error="Please enter roles" disabled="disabled">
									<option value="1">Male</option>
									<option value="0">Female</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6">
								<label>Birthday:</label>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="birthday" type="text"
										class="form-control pull-right" id="datepicker-birth-day"
										readonly>
								</div>
							</div>
							<div class="col-xs-6">
								<label>Pass day:</label>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="deadday" type="text"
										class="form-control pull-rFight" id="datepicker-dead-day"
										readonly>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<label>Address:</label>
								<textarea name="address" class="form-control"
									placeholder="User name" data-error="Please enter username"
									readonly></textarea>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-2">
								<button id="btn-view-people" class="btn btn-block btn-default">
									<span class="glyphicon glyphicon-search"></span> 
								</button>
							</div>
							<div class="col-xs-3">
								<button id="btn-create-people-wife"
									class="btn btn-block btn-info">
									<span class="glyphicon glyphicon-plus"></span> Wife
								</button>
							</div>
							<div class="col-xs-3">
								<button id="btn-create-people-child"
									class="btn btn-block btn-primary">
									<span class="glyphicon glyphicon-plus"></span> Child
								</button>
							</div>
							<div class="col-xs-2">
								<button id="btn-edit-people" class="btn btn-block btn-info">
									<span class="glyphicon glyphicon-wrench"></span>
								</button>
							</div>
							
							<div class="col-xs-2">
								<button id="btn-delete-people" class="btn btn-block btn-danger">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<p></p>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!--Form add new people root-->
	<div class="modal fade" id="form-create-people" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<b id='form-create-child-header'>Please insert new people</b>
					</h4>
				</div>
				<div class="modal-body">
					<form data-toggle="validator" id="form-create-people-submit"
						action="../AddPeopleServlet" method="post"
						enctype="multipart/form-data"  accept-charset="utf-8">
						<input type="hidden" name="id-father"
							class="create-from-people-father" value="-1"> <input
							type="hidden" name="id-genealogy"
							class="create-from-people-genealogy"> <input
							type="hidden" name="command" class="create-from-people-command">
						<div class="form-group">
							<div class="col-xs-7">
								<div class="form-group">
									<label>First name</label> <input name="firstname" type="text"
										class="form-control" placeholder="First name">
								</div>
								<div class="form-group">
									<label>Last name</label> <input name="lastname" type="text"
										class="form-control" placeholder="First name">
								</div>
							</div>
							<div class="col-xs-5">
								<div class="form-group">
									<img src="../img/congai.jpg" id='img-upload-root-people'
										class="w3-circle" style="width: 170px; height: 170px;">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-5">
								<label>Alias</label> <input name="alias" type="text"
									class="form-control" placeholder="Alias">
							</div>
							<div class="col-xs-5">
								<label>Profile</label>
								<div class="input-group">
									<span class="input-group-btn"> <span
										class="btn btn-default btn-file"> <input name="img"
											type="file" id="imgInp-root-people">
									</span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-4">
								<label>Sex</label> <select name="sex" class="form-control"
									data-error="Please enter roles" >
									<option value="1" id="option-1">Male</option>
									<option value="0" id="option-0">Female</option>
								</select>
							</div>
							<div class="col-xs-4">
								<label>Birthday:</label>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="birth" type="text" class="form-control pull-right"
										id="datepicker-birth-day-root-people">
								</div>
							</div>
							<div class="col-xs-4">
								<label>Passday:</label>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="dead" type="text" class="form-control pull-rFight"
										id="datepicker-dead-day-root-people">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<label>Address:</label>
								<textarea name="address" class="form-control"
									placeholder="Address" data-error="Please enter address"></textarea>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<div id='message-form-create-people'
								class="col-sm-offset-2 col-sm-10"></div>
						</div>
						<div class="form-group">

							<button type="submit" class="btn btn-block btn-success">Add</button>
							<button data-dismiss="modal" class="btn btn-block btn-default">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!--Form edit new people -->
	<div class="modal fade" id="form-edit-people" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<b>Edit people</b>
					</h4>
				</div>
				<div class="modal-body">
					<form data-toggle="validator" id="form-edit-people-submit"
						action="../EditPeopleServlet" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="command" class="edit-people-command"
							value=""> <input type="hidden" name="id" class="edit-id">
						<div class="form-group">
							<div class="col-xs-7">
								<div class="form-group">
									<label>First name</label> <input name="firstname" type="text"
										class="form-control" placeholder="First name">
								</div>
								<div class="form-group">
									<label>Last name</label> <input name="lastname" type="text"
										class="form-control" placeholder="First name">
								</div>
							</div>
							<div class="col-xs-5">
								<div class="form-group">
									<img src="../img/congai.jpg" id='img-upload-edit-people'
										class="w3-circle" style="width: 170px; height: 170px;">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-5">
								<label>Alias</label> <input name="alias" type="text"
									class="form-control" placeholder="Alias">
							</div>
							<div class="col-xs-5">
								<label>Profile</label>
								<div class="input-group">
									<span class="input-group-btn"> <span
										class="btn btn-default btn-file"> <input name="img"
											type="file" id="imgInp-edit-people">
									</span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-4">
								<label>Sex</label> <select name="sex" class="form-control"
									data-error="Please enter roles">
									<option value="1">Male</option>
									<option value="0">Female</option>
								</select>
							</div>
							<div class="col-xs-4">
								<label>Birthday:</label>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="birthday" type="text"
										class="form-control pull-right"
										id="datepicker-birth-day-edit-people">
								</div>
							</div>
							<div class="col-xs-4">
								<label>Passday:</label>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="deadday" type="text"
										class="form-control pull-rFight"
										id="datepicker-dead-day-edit-people">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<label>Address:</label>
								<textarea name="address" class="form-control"
									placeholder="Address" data-error="Please enter address"></textarea>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<div id='message-form-edit-people'
								class="col-sm-offset-2 col-sm-10"></div>
						</div>
						<div class="form-group">

							<button type="submit" class="btn btn-block btn-success">Update</button>
							<button data-dismiss="modal" class="btn btn-block btn-default">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>	
	<script src="../myjs/index.js"></script>
</body>
</html>
