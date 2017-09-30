<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <!-- view form people Modal -->
        <div class="modal fade" id="form-view-people" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title" id="myModalLabel">View people model</h4>
                    </div>
                    <div class="modal-body">
                        <form data-toggle="validator" >
                            <input type="hidden" name="command" value="view">
                            <input type="hidden" name="id" class="view-id">
                            <div class="form-group">
                                <div class="col-xs-7">
                                    <div class="form-group">
                                        <label>First name</label>
                                        <input type="text" class="form-control" placeholder="First name" >
                                    </div>
                                    <div class="form-group">
                                        <label>First name</label>
                                        <input type="text" class="form-control" placeholder="First name" >
                                    </div>
                                </div>
                                <div class="col-xs-5">
                                    <div class="form-group">
                                        <img src="../img/congai.jpg" id='img-upload'  class="w3-circle"  style="width: 142px;height: 142px;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-5">
                                    <label>Alias</label>
                                    <input type="text" class="form-control" placeholder="Alias" >
                                </div>
                                <div class="col-xs-5">
                                    <label>Hình ảnh</label>
                                    <div class="input-group">
                                        <span class="input-group-btn">
                                            <span class="btn btn-default btn-file">
                                                <input type="file" id="imgInp">
                                            </span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label>Sex</label>
                                    <select  name="roles" class="form-control" data-error="Please enter roles">
                                        <option value="1">Nam</option>
                                        <option value="0">Nữ</option>
                                    </select>
                                </div>
                                <div class="col-xs-4">
                                    <label>Ngày Sinh:</label>
                                    <div class="input-group date">
                                        <div class="input-group-addon"> <i class="fa fa-calendar"></i>
                                        </div>
                                        <input type="text" class="form-control pull-right" id="datepicker-birth-day">
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <label>Từ trần:</label>
                                    <div class="input-group date">
                                        <div class="input-group-addon"> <i class="fa fa-calendar"></i>
                                        </div>
                                        <input type="text" class="form-control pull-rFight" id="datepicker-dead-day">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <label >Địa Chỉ:</label>
                                    <textarea type="text" name="username" class="form-control" placeholder="User name"  data-error="Please enter username"></textarea>
                                    <div class="help-block with-errors"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <button  data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
                                <button  data-backdrop="static" data-toggle="modal" data-target="#form-create-genealogy" class="btn btn-block btn-warning">Open model</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- form-create-genealogy -->
        <div class="modal fade" id="form-create-genealogy" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title" id="myModalLabel">Create new genealogy</h4>
                    </div>
                    <div class="modal-body">
                        <form data-toggle="validator" >
                            <input type="hidden" name="command" value="view">
                            <input type="hidden" name="id" class="view-id">
                            
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" class="form-control" placeholder="Name genealogy" required >
                                <div class="help-block with-errors"></div>
                            </div>
                            
                            <div class="form-group">
                                <label >Description:</label>
                                <textarea type="text" name="username" class="form-control" placeholder="User name"  data-error="Please enter username" ></textarea>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <button type="submit"  class="btn btn-block btn-success" >Create</button>
                                <button  data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>