
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


    $('#register-form').submit(function (e) {
        var $lg_username = $('#register_username').val();
        var $lg_password = $('#register_password').val();
        var $lg_email = $('#register_email').val();
        
        e.preventDefault();
        alert('register'+$lg_username +"|"+ $lg_password);
        if($lg_username == "" || $lg_password ==""|| $lg_email ==""){
        	$('#div-login-msg').html("Please fill ....");
        }
        $.ajax({
            type: 'post',
            url: '../RegisterServlet',
            data:  $(this).serialize(),
            beforeSend: function () {
            	$('#div-register-msg').html("<img src='../img/ajax-loader.gif' /> Loading...");
            },
            error: function (xhr, ajaxOptions, thrownError) {
            	$('#div-register-msg').html("<p style ='color:red;'>Can't connect to server, try again.</p>");
            },
            success: function (data) {
                if (data == "user") {
                	$('#div-register-msg').html("<p style ='color:red;'>User had been used, try again</p>");
                } else if (data == "email") {
                	$('#div-register-msg').html("<p style ='color:red;'>Email had been used.</a></p>");
                }else if (data == "failed") {
                	$('#div-register-msg').html("<p style ='color:red;'>It's failed.</a></p>");
                } else if (data == "success") {
                	$('#div-register-msg').html("<p style ='color:green;'>Register Success, Login <a href ='login.jsp' > <b>here</b> </a></p>");
                	// document.location.href = 'index.jsp';
                } else {
                    alert(data);
                }
            }
           
        });
        
        return false;
    });
  
});