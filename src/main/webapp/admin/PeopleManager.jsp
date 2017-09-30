<%@page import="Utils.DateUtil"%>
<%@page import="model.People"%>
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
        <small>People Manager</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">People Manager</li>
      </ol>
    </section>

    <!-- Main content -->
      <%
	ArrayList<People> list = new ArrayList<>();
	list = (ArrayList)request.getAttribute("list");
    %>
    <section class="content col-sm-12">
      <!-- Small boxes (Stat box) -->
      <div class="row">
	      <div class="container">
	            <div class="panel panel-default">
	                <div class="panel-heading">People Manager<span id='message' class="pull-right"></div>
	                <%if(request.getAttribute("msg")!=null) {%>
	                	<h1><%=request.getAttribute("msg")%></h1>
	                <%}%>
	                <table class="table ">
	                    <thead>
	                        <tr>
	                            <th class="col-sm-2">#No</th>
	                            <th class="col-sm-2">Name</th>
	                            <th class="col-sm-2">Sex</th>
	                            <th class="col-sm-2">Address</th>
	                            <th class="col-sm-2">Birthday</th>
	                            <th class="text-center col-sm-2">#</th>
	                        </tr>
	                    </thead>
	                    <tbody>	
	                   <% int j=1;
	                    for(People i : list){%>		
	                    	<tr>
	                    		<td><%=j%></td>
	                    		<td><%=i.getFirstname()+ " "+i.getLastname()%></td>
	                    		<%if(i.getSex()==1){%>
	                    		<td>Nam</td>
	                    		<%}else{%>
	                    		<td>Nữ</td>
	                    		<%}%>
	                    		<td><%=i.getAddress() %></td>
	                    		<td><%=DateUtil.getDate1(i.getBirth())%></td>
	                    		<td data-id="' + value.id + '" class="text-center">
	                    		<input type="hidden" value="' + value.roles + '" />	                    
	                    		<button data-backdrop="static" data-toggle="modal" data-target="#edit-item<%=j%>" class="edit-item btn btn-warning btn-xs"><i class="glyphicon glyphicon-edit"></i></button>                    		          				                    		
	                    		</td>	
	                    		 <!-- form-edit-genealogy -->
        <div class="modal fade" id="edit-item<%=j%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">X</span></button>
                        <h4 class="modal-title" id="myModalLabel">Edit People</h4>
                    </div>
                    <div class="modal-body" >
                        <form action="EditPeopleManagerServlet" method ="post">
                            <input type="hidden" name="id" value="<%=i.getId()%>" class="edit-id">
                            <div class="form-group">
                                <label>Fist Name</label>
                                <input name="firstname" type="text" class="form-control" value="<%=i.getFirstname()%>" placeholder="Name genealogy" data-error="Please enter name"  required >
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <label>Last Name</label>
                                <input name="lastname" type="text" class="form-control" value="<%=i.getLastname()%>" placeholder="Name genealogy" data-error="Please enter name"  required >
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
								<label>Sex</label>
								<div>
									<%if(i.getSex()==1){%>
								<select name="sex" class="form-control" id="farmer">
								  <option value="1" selected>Nam</option>
								  <option value="0">Nữ</option>
								</select>
								<%}else{%>
								<select name="sex" class="form-control" id="farmer">
								  <option value="1" >Nam</option>
								  <option value="0" selected>Nữ</option>
								 </select> 
								<%}%>
								</div>
								<div class="help-block with-errors"></div>
							</div>
                            <div class="form-group">
                                <label >Address</label>
                                <input type="text" name="address" class="form-control" value="<%=i.getAddress()%>" ></input>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
								<label for="datepicker">BirthDay</label>
								<input type="text" class="form-control" id="datepicker-birth-day<%=j%>" name="birthday" placeholder="12/12/2017" value="<%=DateUtil.getDate1(i.getBirth())%>">
								<div class="help-block with-errors"></div>
							</div>	
                            <div class="form-group">
								<label for="datepicker">DeadDay</label>
								<input type="text" class="form-control" id="datepicker-dead-day<%=j++%>" name="deadday" placeholder="12/12/2017" value="<%=DateUtil.getDate1(i.getDead())%>">
								<div class="help-block with-errors"></div>
							</div>	
                            <div class="form-group">
                                <button type="submit"  class="btn btn-block btn-success ">Edit</button>
                                <button  data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
	                    		<%}%>
	                    	</tr>		
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
<% int k = 1;
for(People i : list){%>
	<script type="text/javascript">
		$('#datepicker-birth-day<%=k%>').datepicker({
	        autoclose: true
	    });
	    $('#datepicker-dead-day<%=k++%>').datepicker({
	        autoclose: true
	    });
	</script>
<%}%>
