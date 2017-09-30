
/* #####################################################################
 #
 #   Project       : Modal Login with jQuery Effects
 #   Author        : Rodrigo Amarante (rodrigockamarante)
 #   Version       : 1.0
 #   Created       : 07/29/2015
 #   Last Change   : 08/04/2015
 #
 ##################################################################### */

$(function () {


    $('#login-form').submit(function (e) {
        var $lg_username = $('#login_username').val();
        var $lg_password = $('#login_password').val();
        e.preventDefault();
       // alert('login'+$lg_username +"|"+ $lg_password);
        if($lg_username == "" || $lg_password ==""){
        	$('#div-login-msg').html("Please fill ....");
        }
        $.ajax({
            type: 'post',
            url: '../LoginServlet',
            data: {username: $lg_username, password: $lg_password, command: 'login'},
            beforeSend: function () {
            	$('#div-login-msg').html("<img src='../img/ajax-loader.gif' /> Loading...");
            },
            error: function (xhr, ajaxOptions, thrownError) {
            	$('#div-login-msg').html("<p style ='color:red;'>Can't connect to server, try again.</p>");
            },
            success: function (data) {
                if (data == "user") {
                	$('#div-login-msg').html("<p style ='color:red;'>User not correct, try again</p>");
                } else if (data == "pass") {
                	$('#div-login-msg').html("<p style ='color:red;'>Pass not correct.</a></p>");
                }else if (data == "failed") {
                	$('#div-login-msg').html("<p style ='color:red;'>It's failed.</a></p>");
                } else if (data == "success") {
                	$('#div-login-msg').html("<p style ='color:green;'>Login Success, wait to go <a href ='index.jsp' >Home </a></p>");
                	 document.location.href = 'index.jsp';
                } else {
                    alert(data);
                }
            }
           
        });
    });
  
});