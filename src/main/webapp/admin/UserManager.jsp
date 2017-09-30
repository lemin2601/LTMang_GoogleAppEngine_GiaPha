<%@page import="dao.UserDAO"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@include file="header.jsp"%>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Home
        <small>User Manager</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">User Manager</li>
      </ol>
    </section>

    <!-- Main content -->
    <%
	ArrayList<User> list = (ArrayList)request.getAttribute("list");
    %>
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <div class="row">
	      <div class="container">
	            <div class="panel panel-default">
	                <div class="panel-heading">User Manager<span id='message' class="pull-right"></div>
	                <%if(request.getAttribute("msg")!=null) {%>
	                	<h1><%=request.getAttribute("msg")%></h1>
	                <%}%>
	                <table class="table">
	                    <thead>
	                        <tr>
	                            <th>#No</th>
	                            <th>User Name</th>
	                            <th>Email Name</th>
	                            <th>Roles</th>
	                            <th class="text-center">#</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <%
	                    int j=1;
	                    for(User i : list){%>		
	                    	<tr>
	                    		<td><%=j%></td>
	                    		<td><%=i.getUsername()%></td>
	                    		<td><%=i.getEmail()%></td>
	                    		<td><%=i.getRoles()%></td>
	                    		<td data-id="" class="text-center">                  
	                    		<button data-backdrop="static" data-toggle="modal" data-target="#edit-item<%=j%>" class="edit-item btn btn-warning btn-xs"><i class="glyphicon glyphicon-edit"></i></button>
	                    		<a href="DeleteUserServlet?id=<%=i.getId() %>" class="btn btn-danger btn-xs"><i class="remove-item-submit glyphicon glyphicon-trash"></i></a>                    				                    		
	                    		</td>	
	                    	</tr>
	                    	 <!-- form-edit-genealogy -->
        <div class="modal fade" id="edit-item<%=j++%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">X</span></button>
                        <h4 class="modal-title" id="myModalLabel">Edit User</h4>
                    </div>
                    <div class="modal-body" >
                        <form data-toggle="validator" id="form-edit-genealogy-submit" action="EditUserManagerServlet" method = "post">
                            <input type="hidden" name="id" value="<%=i.getId() %>" class="edit-id">
                            <div class="form-group">
                                <label>User Name</label>
                                <input name="username" type="text" value="<%=i.getUsername() %>" class="form-control"required >
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <label >Email</label>
                                <input type="text" name="email" value="<%=i.getEmail()%>" class="form-control" ></input>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <label >Roles</label>
                                <input type="text" name="roles" value="<%=i.getRoles()%>" class="form-control" ></input>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <button type="submit"  class="btn btn-block btn-success " >Edit</button>
                                <button  data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
	                    	<%}%>		
	                    </tbody>
	                </table>	
	                <ul class=" pagination pull-right" id="pagination"></ul>						
	            </div>
	        </div>
	        <ul id="pagination" class="pagination-sm"></ul>
        <div class="col-lg-3 col-xs-6">
        </div>
        <!-- ./col -->
      </div>
      <!-- /.row -->
      <!-- Main row -->
      <!-- /.row (main row) -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.7
    </div>
    <strong>Copyright &copy; 2016-2017 <a href="http://almsaeedstudio.com">Lee Min </a>.</strong> All rights
    you can copy.
  </footer>
</div>
<!-- ./wrapper -->

<!-- modal form -->

       
<%@include file="footer.jsp"%>