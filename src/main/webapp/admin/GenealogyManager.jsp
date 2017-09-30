<%@page import="model.Genealogy"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<jsp:include page="header.jsp"></jsp:include>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Home
        <small>Genealogy Manager</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Genealogy Manager</li>
      </ol>
    </section>

    <!-- Main content -->
     <%
	ArrayList<Genealogy> list = (ArrayList)request.getAttribute("list");
    %>
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <div class="row">
	      <div class="container">
	            <div class="panel panel-default">
	                <div class="panel-heading">Genealogy Manager<span id='message' class="pull-right"></div>
	                <%if(request.getAttribute("msg")!=null) {%>
	                	<h1><%=request.getAttribute("msg")%></h1>
	                <%}%>
	                <table class="table">
	                    <thead>
	                        <tr>
	                            <th>#No</th>
	                            <th>Name Genealogy</th>
	                            <th>Detail</th>
	                            <th class="text-center">#</th>
	                        </tr>
	                    </thead>
	                    <tbody>	
	                    <%
	                    int j=1;
	                    for(Genealogy i : list){%>		
	                    	<tr>
	                    		<td><%=j%></td>
	                    		<td><%=i.getName()%></td>
	                    		<td style=" width: 450px;"><%final String value;
									if (i.getDescription() == null || i.getDescription().length() <= 0) {
									    value = "_";
									} else if (i.getDescription().length() <= 38) {
									    value = i.getDescription();
									} else { 
									    value = i.getDescription().substring(0, 38)+"...";
									}%><%=value%></td>
	                    		<td data-id="' + value.id + '" class="text-center">
	                    		<input type="hidden" value="' + value.roles + '" />	                    
	                    		<button data-backdrop="static" data-toggle="modal" data-target="#edit-item<%=j%>" class="edit-item btn btn-warning btn-xs"><i class="glyphicon glyphicon-edit"></i></button>                 				                    		
	                    		</td>	
	                    	</tr>               		
	                    	<!-- modal form -->
        <!-- form-edit-genealogy -->
        <div class="modal fade" id="edit-item<%=j++%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">X</span></button>
                        <h4 class="modal-title" id="myModalLabel">Edit Genealogy</h4>
                    </div>
                    <div class="modal-body" >
                        <form data-toggle="validator" id="form-edit-genealogy-submit" action="EditGenealogyManagerServlet" method = "post">
                            <input type="hidden" name="id" value="<%=i.getId() %>" class="edit-id">
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" value="<%=i.getName() %>" placeholder="Name genealogy" data-error="Please enter name"  required >
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <label >Description</label>
                                <textarea type="text" name="description" class="form-control" placeholder="Description"><%=i.getDescription() %></textarea>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <div id='message-form-edit-genealogy' class="col-sm-offset-2 col-sm-10"></div>
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


<jsp:include page="footer.jsp"></jsp:include>