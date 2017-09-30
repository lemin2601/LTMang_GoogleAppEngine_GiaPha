//Tiến hành view 
$(document).ready(function () {
	var id_genealogy = -1; // default a lastest genealogy
	var id_people = -1;	// id people root
	var deep = 3;
    //get all list genealgy
	manageDataGenealogy();	//--> view list people
    // view root tree
	viewTreeGenealogy();// -->view tree
	//Hiển thị tên gia phả ra select
    function manageDataGenealogy() {
        console.log("get list genealogy name");
        $.ajax({
            url: '../ViewGenealogyServlet',
            type: 'post',
            dataType: 'json',
            data: {command: 'get_list',id_genealogy:id_genealogy},
            beforeSend: function () {
            	toastr.info('Wait to reload genealogy.', 'Info Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 2000});
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("Can;t to load data, try again");
                // $("#message-form-delete-genealogy").html("<p style ='color:red;'>Can't connect to server, try again.</p>");
            },
            success: function (data) {
            	console.log('get list genealogy nam done.');
            	//if not have any genealogy return;
            	if (typeof data === "undefined") return
            	//if ((Object.keys(data).length === 0)) return;
            	var rows = '';
                $.each(data, function (key, value) {
                    rows = rows + '<option value="' + value.id + '">' + value.name + '</option>';
                });
                $("#guide-name").removeAttr('disabled');
                $("#guide-genealogy").html(rows);
                $('#guide-genealogy').html(rows).selectpicker('refresh');
                id_genealogy = $("#guide-genealogy option:selected").val();
                //get list people with genealogy
                manageDataPeopleByIdGenealogy();
                //$('#btn-xem-gia-pha').click();
            }
        });
    }
    //view list name people with id genealody
    function manageDataPeopleByIdGenealogy() {
        var id = $("#guide-genealogy option:selected").val();
        if(id == null) return; // return if not any selected
        console.log("get list people");
        $.ajax({
            url: '../ViewGenealogyServlet',
            type: 'post',
            dataType: 'json',
            data: {command: 'get_list_by_id', id_genealogy: id},
            beforeSend: function () {
                console.log("get list select people");
                //$("#guide-name").attr('disabled','disabled') ;  
            }
        }).done(function (data) {
        	console.log("get list people done");
        	var rows = '';        
	       	 $.each(data, function (key, value) {
	       		rows = rows + '<option value="' + value.id + '">' + value.firstname + " " + value.lastname + '</option>';
            });           
            $("#guide-name").html(rows);
            $('#guide-name').html(rows).selectpicker('refresh');
            id_people = $("#guide-name option:selected").val();
            //load tree view if done 
            
        });
    }
    
    //update people list when have any change select
    $("#guide-genealogy").change(function (e) {
    	console.log('update people list');
    	id_genealogy = $("#guide-genealogy option:selected").val();
//        console.log($("#guide-genealogy option:selected").val());
//        toastr.info('Wait to reload people in genealogy.', 'Info Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 2000});
        manageDataPeopleByIdGenealogy();
    });
    $("#guide-name").change(function (e) {
    	id_people = $("#guide-name option:selected").val();
    });
    $("#guide-deep-genealogy").change(function (e) {
    	deep = $("#guide-deep-genealogy option:selected").val();
    });

    //View tree genealogy
    function viewTreeGenealogy() {
    	//gửi lên:
        //1. id gia phả
        //2. id người mà muốn view. mặc định là người lớn nhất.
//        var id_genealogy = $("#guide-genealogy option:selected").val();
//        var id_people = $("#guide-name option:selected").val();
        var deep = $("#guide-deep-genealogy option:selected").val();
    	
        console.log('start view tree genealogy' +"|"+id_genealogy+"|"+id_people +"|"+ deep);
        $.ajax({
            url: '../ViewGenealogyServlet',
            type: 'post',
            dataType: 'json',
            data: {command: 'get_data_view_grid', id_genealogy: id_genealogy, id_people: id_people, deep: deep},
            beforeSend: function () {
                //console.log(id_genealogy+"|"+id_people+"|"+deep);
            }
        }).done(function (data) {
        	console.log('start view tree genealogy done');
        	viewInforGenealogy(data.genealogy);
        	console.log(data.people);
        	if (typeof data.people === "undefined") {
        		console.log('view tree genealogy null');
        		addButtonCreateRootPeople();
        		$('#tree').html('');
        	}else
        	{
        		$('#tree').html(taoCay(data.people));   
        		$('#btn-show-form-create-first-people').remove();
        		addEventItemClick();  
        	}
        	    
        });
    }
    function viewInforGenealogy(data) {
    	$("#view-genealogy-name").html(data.name);
        $("#view-genealogy-id").val(data.id);
        $("#view-genealogy-description").html(data.description);
    }
    function addEventItemClick(){
    	$(".btn-show-form-create-people").dblclick(function () {
            console.log("click to view form people");
            $('#form-view-people').modal({
                backdrop: 'true',
                //keyboard: false
            });               
            //set data tại đây
            var id_people = $(this).attr('data-id');
            $.ajax({
                url: '../ViewPeopleServlet',
                type: 'post',
                dataType: 'json',
                data: {command: 'get_item_view_edit', id_people: id_people},
                beforeSend: function () {
                    console.log(id_genealogy+"|"+id_people+"|"+deep);
                }
            }).done(function (data) {
            	console.log(data);
                if(data.people == null){                  	
                	$("#form-view-people-submit").find("input[name='firstname']").val(data.firstname);
                	$("#form-view-people-submit").find("input[name='lastname']").val(data.lastname);
                	$("#form-view-people-submit").find("input[name='alias']").val(data.alias);
                	$("#form-view-people-submit").find("select[name='sex']").val(data.sex);
                	$("#form-view-people-submit").find("input[name='birthday']").val(data.birthday);
                	$("#form-view-people-submit").find("input[name='deadday']").val(data.deadday);;
                    $("#form-view-people-submit").find("textarea[name='address']").val(data.address);
                    $("#form-view-people-submit").find("img").attr('src','../ViewImageServlet?img='+data.id);
                    $("#form-view-people-submit").find(".view-id").val(data.id);   
                    if(data.sex != '1'){
                        $("#btn-create-people-wife").attr("disabled",'disable');
                        $("#btn-create-people-child").attr("disabled",'disable');
                    }else{
                    	 $("#btn-create-people-wife").removeAttr("disabled");
                         $("#btn-create-people-child").removeAttr("disabled");
                    }
                }
           });
        });
    }
    function addButtonCreateRootPeople(){
		console.log("dang tao cay");
		 //$('#form-create-people-root').modal(); 
		 //if($('#btn-show-form-create-first-people') != null) return;
		 var div = $( '#show-btn-insert-first-people' );
		 div.html('<button id="btn-show-form-create-first-people" type="button" class="btn btn-block btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Thêm Người Đầu Tiên</button>');
		 $('#btn-show-form-create-first-people').click(function () {
	        console.log("click to view form giapha");
	        //jQuery.noConflict(); 
	        $('#form-create-people').modal({
	            backdrop: 'static',
	            keyboard: false
	        });
	        var id_genealogy = $("#view-genealogy-id").val();
	        $("#form-create-people-submit").find(".create-from-people-father").val("-1");
	        $("#form-create-people-submit").find(".create-from-people-genealogy").val($("#view-genealogy-id").val());
	        $("#form-create-people-submit").find(".create-from-people-command").val('root'); 
	        $('#form-create-child-header').html("Add First People");
	        $("#option-1").slected = true;
	        $("#option-0").attr('disabled','disabled');
	        $("#option-1").removeAttr('disabled');
	    });    	
    }
    $('#btn-xem-gia-pha').click(function () {
    	viewTreeGenealogy();
    });

    function taoCay(people) {
    	//console.log(people==null);
        var result = '<ul><li>';
        $.each(people, function (key, value) {
            if (key == 'cha') {
                result += taoProfileFromCha(value);
            } else {
                result += taoChildFromCon(value);
            }
        });
        result += '</li></ul>';
        return result;
    }
    function taoProfileFromCha(cha) {
        var result = '<a>';
        $.each(cha, function (key, value) {
            if (key == 'item')
                result += taoProfileCaNhan(value);
        });
        result += '</a>';
        return result;
    }
    function taoProfileCaNhan(profile) {
        var item = '';
        $.each(profile, function (key, value) {
            item = item + '<div sex-id="'+value.sex+'" data-id="'+value.id+'" '+(value.sex =='1'?'type="button" class="btn btn-info':'type="button" class="btn')+' btn-show-form-create-people" style=" display: inline-block;" data-toggle="tooltip" title="'+value.address+'" data-placement="bottom" >';
            item = item + '<img class="w3-circle" style="width: 100px;height: 100px;" src="../ViewImageServlet?img='+value.id+'" alt="' + value.firstname +' '+ value.lastname+'">';
            item = item + '<div class="w3-container">';
            item = item + '<h4><b><font size="4">' + value.firstname +' '+ value.lastname+'</font></b></h4>';
            item = item + '<p>'+value.birthday +' - '+value.deadday +'</p>';
            item = item + '</div>';
            item = item + '</div>';
        });
        return item;
    }
    function taoChildFromCon(con) {
    	if (jQuery.isEmptyObject(con))
        {
           console.log("Empty Object");
           return '';
        }
    	//if (typeof con.value === "undefined") return '';
        var result = '<ul>';
        $.each(con, function (key, value) {
            result += taoProfileChildFromCon(value);
        });
        result += '</ul>';
        return result;
    }
    function taoProfileChildFromCon(con) {
        var result = '<li>';
        $.each(con, function (key, value) {
            if (key == 'cha')
                result += taoProfileFromCha(value);
            if (key == 'con') {
                result += taoChildFromCon(value);
            }
        });
        result += '</li>';
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////GENEALOGY/////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
    $('#datepicker-birth-day').datepicker({
        autoclose: true
    });
    $('#datepicker-dead-day').datepicker({
        autoclose: true
    });  
    $("#btn-show-form-create-genealogy").click(function () {
        console.log("click to view form giapha");
        //jQuery.noConflict(); 
        $('#form-create-genealogy').modal({
            backdrop: 'static',
            keyboard: false
        });
    });
    //show info form edit genealogy
    $("#btn-show-form-edit-genealogy").click(function () {
        console.log("click to edit genealogy");
        $('#form-edit-genealogy').modal({
            backdrop: 'static',
            keyboard: false
        });  
        $("#form-edit-genealogy-submit").find("input[name='name']").val($("#view-genealogy-name").text());
        $("#form-edit-genealogy-submit").find("textarea[name='description']").val($("#view-genealogy-description").text());
        $("#form-edit-genealogy-submit").find(".edit-id").val($("#view-genealogy-id").val());    
    });
    
    //show infor form delete genealogy
    $("#btn-show-form-delete-genealogy").click(function () {
        console.log("click to delte giapha");
        $('#form-delete-genealogy').modal({
            backdrop: 'true',
            //keyboard: false
        });
//        var id_genealogy = $("#guide-genealogy option:selected").val();
//        var name_genealogy = $("#guide-genealogy option:selected").text();
        $("#form-delete-genealogy-submit").find("input[name='name']").val($("#view-genealogy-name").text());
        $("#form-delete-genealogy-submit").find(".delete-id").val($("#view-genealogy-id").val());
    });
    
    //Edit genealogy submit
    $("#form-edit-genealogy-submit").submit(function (e) {
    	console.log('update genealogy submit');
        e.preventDefault();
        var url = $(this).attr('action');
        var type = $(this).attr('method');
        //validate 
        var command = $(this).find("input[name='command']").val();
        var name = $(this).find("input[name='name']").val();
        var description = $(this).find("textarea[name='description']").val();
        if (name == "")
            return;
        console.log(url + "|" + type + "|" + command + "|" + name + "|" + description);
        $.ajax({
            url: url,
            type: type,
            data: $(this).serialize(),
            beforeSend: function () {
                $("#message-form-edit-genealogy").html("<img src='../img/ajax-loader.gif' /> Loading...");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                $("#message-form-edit-genealogy").html("<p style ='color:red;'>Can't connect to server, try again.</p>");
            },
            success: function (data) {
                if (data == "name") {
                    $("#message-form-edit-genealogy").html("<p style ='color:red;'>Name not empty, try again</p>");
                } else if (data == "failed") {
                    $("#message-form-edit-genealogy").html("<p style ='color:red;'>It's failed.</a></p>");
                } else if (data == "success") {
                    $("#message-form-edit-genealogy").html("");
                    $('#form-edit-genealogy').modal('hide');
                    toastr.success('Update Genealogy Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
                    viewTreeGenealogy();// update tree
                    manageDataGenealogy();// update list
                    $("#guide-genealogy option:selected").val( $("#form-edit-genealogy-submit").find(".edit-id").val());
                    
                } else {
                    alert(data);
                }
            }
        });
        return false;
    });
    //Delete genealogy
    $("#form-delete-genealogy-submit").submit(function (e) {
    	console.log('delete genealogy submit');
        e.preventDefault();
        var url = $(this).attr('action');
        var type = $(this).attr('method');
        $.ajax({
            url: url,
            type: type,
            data: $(this).serialize(),
            beforeSend: function () {
                $("#message-form-delete-genealogy").html("<img src='../img/ajax-loader.gif' /> Loading...");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                $("#message-form-delete-genealogy").html("<p style ='color:red;'>Can't connect to server, try again.</p>");
            },
            success: function (data) {
                if (data == "name") {
                    $("#message-form-delete-genealogy").html("<p style ='color:red;'>Name not empty, try again</p>");
                } else if (data == "failed") {
                    $("#message-form-delete-genealogy").html("<p style ='color:red;'>It's failed.</a></p>");
                } else if (data == "success") {
                    $("#message-form-delete-genealogy").html("<p style ='color:green;'></p>");
                    toastr.success('Delete Genealogy Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
                    location.reload();
                } else {
                    alert(data);
                }
            }
        });
        return false;
    });
    
  //Create new genealogy */
  $("#form-create-genealogy-submit").submit(function (e) {
      e.preventDefault();
      var url = $(this).attr('action');
      var type = $(this).attr('method');
      //validate 
      var command = $(this).find("input[name='command']").val();
      var name = $(this).find("input[name='name']").val();
      var description = $(this).find("textarea[name='description']").val();
      if (name == "") {
          //$("#message-form-create-genealogy").html("<p style ='color:red;'>Name not empty, try again.</p>");
          return;
      }
      console.log(url + "|" + type + "|" + command + "|" + name + "|" + description);
      $.ajax({
          url: url,
          type: type,
          data: $(this).serialize(),
          beforeSend: function () {
              $("#message-form-create-genealogy").html("<img src='../img/ajax-loader.gif' /> Loading...");
          },
          error: function (xhr, ajaxOptions, thrownError) {
              $("#message-form-create-genealogy").html("<p style ='color:red;'>Can't connect to server, try again.</p>");
          },
          success: function (data) {
              if (data == "name") {
                  $("#message-form-create-genealogy").html("<p style ='color:red;'>Name not empty, try again</p>");
              } else if (data == "failed") {
                  $("#message-form-create-genealogy").html("<p style ='color:red;'>It's failed.</a></p>");
              } else if (data == "success") {
                  $("#message-form-create-genealogy").html("<p style ='color:green;'>Add success</p>");
                  toastr.success('Genealogy Created Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
                  document.location.href = 'index.jsp';
                  //$("#form-create-genealogy-submit").trigger("reset");
              } else {
                  alert(data);
              }
          }
      });
      return false;
  });
  ////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////PEOPLE/////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
  function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img-upload').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}
	function readURL1(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img-upload-root-people').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function readURL2(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img-upload-edit-people').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$("#imgInp").change(function() {
		readURL(this);
	});

	$("#imgInp-root-people").change(function() {
		readURL1(this);
	});
	$("#imgInp-edit-people").change(function() {
		readURL2(this);
	});
	$('#datepicker-birth-day-root-people').datepicker({
		autoclose : true
	});
	$('#datepicker-dead-day-root-people').datepicker({
		autoclose : true
	});
	$('#datepicker-birth-day-edit-people').datepicker({
		autoclose : true
	});
	$('#datepicker-dead-day-edit-people').datepicker({
		autoclose : true
	});
	
	//phần show form
	 $("#btn-edit-people").click(function () {
	        console.log("click to edit form people");
	        //jQuery.noConflict(); 
	        $('#form-edit-people').modal({
	            backdrop: 'static',
	            keyboard: false
	        });	        
	        //bỏ data
//	        var id_people = $("#form-view-people-submit").find(".view-id").val();
//           $.ajax({
//               url: '../ViewPeopleServlet',
//               type: 'post',
//               dataType: 'json',
//               data: {command: 'get_item_view_edit', id_people: id_people},
//               
//           }).done(function (data) {
//           	console.log(data);
//               if(data.people == null){                  	
//               	$("#form-edit-people-submit").find("input[name='firstname']").val(data.firstname);
//               	$("#form-edit-people-submit").find("input[name='lastname']").val(data.lastname);
//               	$("#form-edit-people-submit").find("input[name='alias']").val(data.alias);
//               	$("#form-edit-people-submit").find("select[name='sex']").val(data.sex);
//               	$("#form-edit-people-submit").find("input[name='birthday']").val(data.birthday);
//               	$("#form-edit-people-submit").find("input[name='deadday']").val(data.deadday);;
//                   $("#form-edit-people-submit").find("textarea[name='address']").val(data.address);
//                   $("#form-edit-people-submit").find(".edit-id").val(data.id);   
//               }
//          });
        $("#form-edit-people-submit").find("input[name='firstname']").val($("#form-view-people-submit").find("input[name='firstname']").val());
        $("#form-edit-people-submit").find("input[name='lastname']").val($("#form-view-people-submit").find("input[name='lastname']").val());
       	$("#form-edit-people-submit").find("input[name='alias']").val($("#form-view-people-submit").find("input[name='alias']").val());
       	$("#form-edit-people-submit").find("select[name='sex']").val($("#form-view-people-submit").find("select[name='sex']").val());
       	$("#form-edit-people-submit").find("input[name='birthday']").val($("#form-view-people-submit").find("input[name='birthday']").val());
       	$("#form-edit-people-submit").find("input[name='deadday']").val($("#form-view-people-submit").find("input[name='deadday']").val());;
        $("#form-edit-people-submit").find("textarea[name='address']").val( $("#form-view-people-submit").find("textarea[name='address']").val());
        $("#form-edit-people-submit").find(".edit-id").val($("#form-view-people-submit").find(".view-id").val());  
        $("#form-edit-people-submit").find(".w3-circle").attr('src',$("#form-view-people-submit").find(".w3-circle").attr('src')); 
        $('#form-view-people').modal('hide');
	        return false;
	    });
	 
	 $("#btn-create-people-wife").click(function () {
	        console.log("click to edit form people");
	        //jQuery.noConflict(); 
	        $('#form-create-people').modal({
	            backdrop: 'static',
	            keyboard: false
	        });	        
	        //set command add wife
	        var id_genealogy = $("#view-genealogy-id").val();
	        $("#form-create-people-submit").find(".create-from-people-father").val($("#form-view-people-submit").find(".view-id").val());
	        $("#form-create-people-submit").find(".create-from-people-genealogy").val(id_genealogy);
	        $("#form-create-people-submit").find(".create-from-people-command").val('wife'); 
	        $('#form-create-child-header').html("Add New Wife");
	        $('#form-create-child-header').find("select[name='sex']").val('0');
	        $('#form-create-child-header').find("select[name='sex']").attr('disabled','disabled');
	        $("#option-0").slected = true;
	        $("#option-1").attr('disabled','disabled');
	        $("#option-0").removeAttr('disabled');
	        return false;
	    });
	 $("#btn-create-people-child").click(function () {
	        console.log("click to edit form people");
	        //jQuery.noConflict(); 
	        $('#form-create-people').modal({
	            backdrop: 'static',
	            keyboard: false
	        });	        
	        //set command add child
	        var id_genealogy = $("#view-genealogy-id").val();
	        $("#form-create-people-submit").find(".create-from-people-father").val($("#form-view-people-submit").find(".view-id").val());
	        $("#form-create-people-submit").find(".create-from-people-genealogy").val(id_genealogy);
	        $("#form-create-people-submit").find(".create-from-people-command").val('child'); 
	        $('#form-create-child-header').html("Add New Child");
	        $("#option-0").removeAttr('disabled');
	        $("#option-1").removeAttr('disabled');
	        return false;
	    });
	 
	//Thêm people
		$("#form-create-people-submit").submit(function (e) {
		    e.preventDefault();
		    var url = $(this).attr('action');
		    var type = $(this).attr('method');
		    //validate 
		    var firstname = $(this).find("input[name='firstname']").val();
		    var lastname = $(this).find("input[name='lastname']").val();
		    var birthday = $(this).find("input[name='birth']").val();
		    var sex = $(this).find("select[name='sex']").val();
			   
		    if (firstname == ""||lastname == ""||birthday == ""||sex=="") {
		    	console.log(firstname+"|"+lastname+"|"+ birthday+"|"+sex);
		        $("#message-form-create-people").html("<p style ='color:red;'>Plead fill FirstName, LastName,Sex and Birthday.</p>");
		        return false;
		    }
		    console.log("start add new people");		 
	        event.preventDefault();
		    $.ajax({
		        url: url,
		        type: type,
		        //contentType: 'multipart/form-data; charset=UTF-8;boundary=gc0p4Jq0M2Yt08jU534c0p', 
		        cache: false,
		        contentType: false,
		        processData: false,
		        data:  new FormData($(this)[0]),
		        beforeSend: function () {
		            $("#message-form-create-people").html("<img src='../img/ajax-loader.gif' /> Loading...");
		        },
		        error: function (xhr, ajaxOptions, thrownError) {
		            $("#message-form-create-people").html("<p style ='color:red;'>Can't connect to server, try again.</p>");
		        },
		        success: function (data) {
		            if (data.includes("failed")) {
		            	$("#message-form-create-people").html("<p style ='color:red;'>Plead fill FirstName, LastName,Sex and Birthday.</p>");
		            } else if (data.includes("success")) {
		                $("#message-form-create-people").html("<p style ='color:green;'>Add success</p>");
		                toastr.success('People Created Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});			            
		                viewTreeGenealogy();
		                manageDataPeopleByIdGenealogy();
		            } else {
		                alert(data);
		            }
		            
		        }
		    });
		    return false;
		});
		//.Thêm
		// sửa
		$("#form-edit-people-submit").submit(function (e) {
		    e.preventDefault();
		    var url = $(this).attr('action');
		    var type = $(this).attr('method');
		    //validate 
		    var firstname = $(this).find("input[name='firstname']").val();
		    var lastname = $(this).find("input[name='lastname']").val();
		    var birthday = $(this).find("input[name='birthday']").val();
		   
		    if (firstname == ""||lastname == ""||birthday == "") {
		    	console.log(firstname+"|"+lastname+"|"+ birthday);
		        $("#message-form-edit-people").html("<p style ='color:red;'>Plead fill FirstName, LastName and Birthday.</p>");
		        return false;
		    }
		    $.ajax({
		    	url: url,
		        type: type,
		        //contentType: 'multipart/form-data; charset=UTF-8;boundary=gc0p4Jq0M2Yt08jU534c0p', 
		        cache: false,
		        contentType: false,
		        processData: false,
		        data:  new FormData($(this)[0]),
		        beforeSend: function () {
		            $("#message-form-edit-people").html("<img src='../img/ajax-loader.gif' /> Loading...");
		        },
		        error: function (xhr, ajaxOptions, thrownError) {
		            $("#message-form-edit-people").html("<p style ='color:red;'>Can't connect to server, try again.</p>");
		        },
		        success: function (data) {
	                if (data.includes("failed")) {
		            	$("#message-form-edit-people").html("<p style ='color:red;'>Plead fill FirstName, LastName,Sex and Birthday.</p>");
		            } else if (data.includes("success")) {
		                $("#message-form-edit-people").html("<p style ='color:green;'>Add success</p>");
		                toastr.success('People Created Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});			            
		                viewTreeGenealogy();
		                $('#form-edit-people').modal('hide');
		                manageDataPeopleByIdGenealogy();
		            } else {
		                alert(data);
		            }
		        }
		    });
		    return false;
		});
		//.sửa
		//xóa
		 $("#btn-delete-people").click(function () {
		        console.log("click to delete form people");      
		        var commit = confirm("Are you want to delete "+
		        		$("#form-view-people-submit").find("input[name='firstname']").val()+ " "+
		        		$("#form-view-people-submit").find("input[name='lastname']").val() +"\n It's also will delete all off child and wife.\n Are you sure???");
		        if(commit == true){
		        	$.ajax({
		                url: '../DeletePeopleServlet',
		                type: 'post',
		                data: {id_people:$("#form-view-people-submit").find(".view-id").val()},
		                error: function (xhr, ajaxOptions, thrownError) {
		                	toastr.error("Can't to connect to server", 'Error Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
		                },
		                success: function (data) {
		                    if (data == "failed") {
		                    	toastr.error('Delete People Successfully.', 'Error Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
		                    } else if (data.includes("success")) {
		                        $('#form-view-people').modal('hide');
		                        toastr.success('Update Genealogy Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
		                        viewTreeGenealogy();
		                        manageDataPeopleByIdGenealogy();
		                    } else {
		                        alert(data);
		                    }
		                }
		            });
		        }
		        //set command add wife
		        return false;
		    });
		//.xóa
		// View tree people
		 $('#btn-view-people').click(function(){
			 id_genealogy = $("#view-genealogy-id").val();
			 id_people = $("#form-view-people-submit").find(".view-id").val();
			 //$("#guide-name").val(id_people);
			 $('select[id=guide-name]').val(id_people);
			 $('.selectpicker').selectpicker('refresh');
			 $('#btn-xem-gia-pha').click();
			 $('#form-view-people').modal('hide');
			 return false;
		 });
		
    
});
$(document).on('show.bs.modal', '.modal', function () {
    var zIndex = 1040 + (10 * $('.modal:visible').length);
    $(this).css('z-index', zIndex);
    setTimeout(function() {
        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
    }, 0);
});








































// $("#message-form-create-genealogy").html("<img src='../img/ajax-loader.gif' /> Loading...");
//$("#form-edit-genealogy").find("textarea[name='description']").val("roles");
//        $("#form-edit-genealogy").find(".edit-id").val("id-edit");
// toastr.success('Item Created Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
//toastr.error('Item Created Successfully.', 'Success Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 3000});
//toastr.info('Wait to reload people in genealogy.', 'Info Alert', {positionClass: 'toast-bottom-right'}, {timeOut: 2000});
