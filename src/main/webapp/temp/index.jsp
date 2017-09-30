<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Dashboard</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../bootstrap/css/w3.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="../plugins/iCheck/flat/blue.css">
        <!-- Morris chart -->
        <link rel="stylesheet" href="../plugins/morris/morris.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="../plugins/jvectormap/jquery-jvectormap-1.2.2.css">
        <!-- Date Picker -->
        <link rel="stylesheet" href="../plugins/datepicker/datepicker3.css">
        <!-- Daterange picker -->
        <link rel="stylesheet" href="../plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap wysihtml5 - text editor -->
        <link rel="stylesheet" href="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style type="text/css">
            /*Now the CSS*/
            .tree ul {
                padding-top: 20px; position: relative;
                justify-content: center;
                transition: all 0.5s;
                -webkit-transition: all 0.5s;
                -moz-transition: all 0.5s;
            }
            .tree li {
                float: left; text-align: center;
                list-style-type: none;
                position: relative;
                padding: 20px 5px 0 5px;

                transition: all 0.5s;
                -webkit-transition: all 0.5s;
                -moz-transition: all 0.5s;
            }
            /*We will use ::before and ::after to draw the connectors*/
            .tree li::before, .tree li::after{
                content: '';
                position: absolute; top: 0; right: 50%;
                border-top: 1px solid #ccc;
                width: 50%; height: 20px;
            }
            .tree li::after{
                right: auto; left: 50%;
                border-left: 1px solid #ccc;
            }
            /*We need to remove left-right connectors from elements without
            any siblings*/
            .tree li:only-child::after, .tree li:only-child::before {
                display: none;
            }
            /*Remove space from the top of single children*/
            .tree li:only-child{ padding-top: 0;}
            /*Remove left connector from first child and
            right connector from last child*/
            .tree li:first-child::before, .tree li:last-child::after{
                border: 0 none;
            }
            /*Adding back the vertical connector to the last nodes*/
            .tree li:last-child::before{
                border-right: 1px solid #ccc;
                border-radius: 0 5px 0 0;
                -webkit-border-radius: 0 5px 0 0;
                -moz-border-radius: 0 5px 0 0;
            }
            .tree li:first-child::after{
                border-radius: 5px 0 0 0;
                -webkit-border-radius: 5px 0 0 0;
                -moz-border-radius: 5px 0 0 0;
            }
            /*Time to add downward connectors from parents*/
            .tree ul ul::before{
                content: '';
                position: absolute; top: 0; left: 50%;
                border-left: 1px solid #ccc;
                width: 0; height: 20px;
            }
            .tree li a{
                border: 1px solid #ccc;
                padding: 5px 10px;
                text-decoration: none;
                color: #666;
                font-family: arial, verdana, tahoma;
                font-size: 11px;
                display: inline-block;

                border-radius: 5px;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;

                transition: all 0.5s;
                -webkit-transition: all 0.5s;
                -moz-transition: all 0.5s;
            }

            /*Time for some hover effects*/
            /*We will apply the hover effect the the lineage of the element also*/
            .tree li a:hover, .tree li a:hover+ul li a {
                background: #c8e4f8; color: #000; border: 1px solid #94a0b4;
            }
            /*Connector styles on hover*/
            .tree li a:hover+ul li::after,
            .tree li a:hover+ul li::before,
            .tree li a:hover+ul::before,
            .tree li a:hover+ul ul::before{
                border-color:  #94a0b4;
            }

            /*Thats all. I hope you enjoyed it.
            Thanks :)*/
        </style>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <header class="main-header">
                <!-- Logo -->
                <a href="index2.html" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>A</b>LT</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Admin</b>LTE</span>
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">

                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">Alexander Pierce</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                        <p>
                                            Alexander Pierce - Web Developer
                                            <small>Member since Nov. 2012</small>
                                        </p>
                                    </li>
                                    <!-- Menu Body -->
                                    <li class="user-body">
                                        <div class="row">
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Followers</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Sales</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Friends</a>
                                            </div>
                                        </div>
                                        <!-- /.row -->
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="active treeview">
                            <a href="#">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                                <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
                            </ul>
                        </li>

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                        <small>Control panel</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Dashboard</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">


                    <!-- /.row (main row) -->
                    <div class="row" style="justify-content: center;">
                        <section class="col-lg-12 connectedSortable">
                            <div class="tree" style="height: 250px; width: 100%;zoom: 50%">
                                <ul>
                                    <li>
                                        <a href="#" class="btn" role="button">
                                            <div style=" display: inline-block;" class="btn-info" data-toggle="tooltip" title="Đà nẵng" data-placement="bottom">                          
                                                <img class="w3-circle" style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                <div class="w3-container">
                                                    <h4><b>Ông</b></h4>
                                                    <p>26/01/1995 - 20/20/2200</p>
                                                </div>
                                            </div>
                                            <div style=" display: inline-block;" class="btn-warning" data-toggle="tooltip" title="Quảng nam" data-placement="bottom">
                                                <img class="w3-circle" style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                <div class="w3-container">
                                                    <h4><b>Bà</b></h4>
                                                    <p>26/01/1995 - 20/20/2200</p>
                                                </div>
                                            </div>
                                            <div style=" display: inline-block;" class="btn-danger">
                                                <img class="w3-circle" style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                <div class="w3-container">
                                                    <h4><b>Bà</b></h4>
                                                    <p>26/01/1995 - 20/20/2200</p>
                                                </div>
                                            </div>
                                        </a>
                                        <ul>
                                            <li>
                                                <a href="#" class="btn btn-info" role="button">
                                                    <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                    <div class="w3-container">
                                                        <h4><b>Lê Minh Trung</b></h4>
                                                        <p>26/01/1995 - 20/20/2200</p>
                                                    </div>

                                                </a>
                                                <ul>
                                                    <li>
                                                        <a href="#" class="btn btn-info" role="button">
                                                            <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                            <div class="w3-container">
                                                                <h4><b>Lê Minh Trung</b></h4>
                                                                <p>26/01/1995 - 20/20/2200</p>
                                                            </div>

                                                        </a>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li>
                                                <a href="#" class="btn btn-info" role="button">
                                                    <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                    <div class="w3-container">
                                                        <h4><b>Lê Minh Trung</b></h4>
                                                        <p>26/01/1995 - 20/20/2200</p>
                                                    </div>

                                                </a>
                                                <ul>
                                                    <li><a href="#" class="btn btn-info" role="button">
                                                            <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                            <div class="w3-container">
                                                                <h4><b>Lê Minh Trung</b></h4>
                                                                <p>26/01/1995 - 20/20/2200</p>
                                                            </div>

                                                        </a></li>
                                                    <li>
                                                        <a href="#" class="btn btn-info" role="button">
                                                            <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                            <div class="w3-container">
                                                                <h4><b>Lê Minh Trung</b></h4>
                                                                <p>26/01/1995 - 20/20/2200</p>
                                                            </div>

                                                        </a>
                                                        <ul>
                                                            <li>
                                                                <a href="#" class="btn btn-info" role="button">
                                                                    <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                                    <div class="w3-container">
                                                                        <h4><b>Lê Minh Trung</b></h4>
                                                                        <p>26/01/1995 - 20/20/2200</p>
                                                                    </div>

                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="#" class="btn btn-info" role="button">
                                                                    <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                                    <div class="w3-container">
                                                                        <h4><b>Lê Minh Trung</b></h4>
                                                                        <p>26/01/1995 - 20/20/2200</p>
                                                                    </div>

                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="#" class="btn btn-info" role="button">
                                                                    <img style="width: 100px;height: 100px;" src="img/profile.jpg" alt="Person">
                                                                    <div class="w3-container">
                                                                        <h4><b>Lê Minh Trung</b></h4>
                                                                        <p>26/01/1995 - 20/20/2200</p>
                                                                    </div>

                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li><a href="#">Grand Child</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <script>
                                $(document).ready(function () {
                                    $('[data-toggle="tooltip"]').tooltip();
                                });
                            </script>
                        </section>
                    </div>
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.3.7
                </div>
                <strong>Copyright &copy; 2014-2016 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights
                reserved.
            </footer>

            <!-- Control Sidebar -->

            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 2.2.3 -->
        <script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- jQuery UI 1.11.4 -->
        <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
                                $.widget.bridge('uibutton', $.ui.button);
        </script>
        <!-- Bootstrap 3.3.6 -->
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <!-- Morris.js charts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="../plugins/morris/morris.min.js"></script>
        <!-- Sparkline -->
        <script src="../plugins/sparkline/jquery.sparkline.min.js"></script>
        <!-- jvectormap -->
        <script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <!-- jQuery Knob Chart -->
        <script src="../plugins/knob/jquery.knob.js"></script>
        <!-- daterangepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="../plugins/daterangepicker/daterangepicker.js"></script>
        <!-- datepicker -->
        <script src="../plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
        <!-- Slimscroll -->
        <script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="../plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="../dist/js/app.min.js"></script>
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="../dist/js/pages/dashboard.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="../dist/js/demo.js"></script>
    </body>
</html>
