<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <!-- form-view-genealogy -->
        <div class="modal fade" id="form-view-genealogy" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">Ã</span></button>
                        <h4 class="modal-title" id="myModalLabel">Create new genealogy</h4>
                    </div>
                    <div class="modal-body">
                        <form data-toggle="validator" class="form-view-genealogy" >
                            <input type="hidden" name="command" value="view">
                            <input type="hidden" name="id" class="view-id">
                            
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" placeholder="Name genealogy" required readonly >
                                <div class="help-block with-errors"></div>
                            </div>
                            
                            <div class="form-group">
                                <label >Description:</label>
                                <textarea type="text" name="description" class="form-control" placeholder="Description" readonly></textarea>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <button  data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- form-edit-genealogy -->
        <div class="modal fade" id="form-edit-genealogy" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">Ã</span></button>
                        <h4 class="modal-title" id="myModalLabel">Create new genealogy</h4>
                    </div>
                    <div class="modal-body">
                        <form data-toggle="validator" class="form-edit-genealogy" >
                            <input type="hidden" name="command" value="edit">
                            <input type="hidden" name="id" class="edit-id">
                            
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" placeholder="Name genealogy" required >
                                <div class="help-block with-errors"></div>
                            </div>
                            
                            <div class="form-group">
                                <label >Description:</label>
                                <textarea type="text" name="description" class="form-control" placeholder="Description" ></textarea>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="form-group">
                                <div id='message-form-edit-genealogy' class="col-sm-offset-2 col-sm-10"></div>
                            </div>
                            <div class="form-group">
                                <button type="submit"  class="btn btn-block btn-success" >Save</button>
                                <button  data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Add Forefather-->
        <div class="modal fade" id="form-create-forefather" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">Ã</span></button>
                        <h4 class="modal-title" id="myModalLabel">Create Forefather</h4>
                    </div>
                    <div class="modal-body">
                        <form data-toggle="validator" class="form-create-forefather">
                            <input type="hidden" name="command" value="create">
                            <input type="hidden" name="id" class="create-id">
                            <div class="form-group">
                                <div class="col-xs-7">
                                    <div class="form-group">
                                        <label>First name</label>
                                        <input name="firstname" type="text" class="form-control" placeholder="First name" required >
                                        <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="form-group">
                                        <label>Last name</label>
                                        <input name="lastname" type="text" class="form-control" placeholder="Last name" required >
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-xs-5">
                                    <div class="form-group">
                                        <img src="../img/congai.jpg" id='img-upload-form-create-forefather'  class="w3-circle"  style="width: 142px;height: 142px;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-5">
                                    <label>Alias</label>
                                    <input name="alias" type="text" class="form-control" placeholder="Alias" >
                                </div>
                                <div class="col-xs-5">
                                    <label>HÃ¬nh áº£nh</label>
                                    <div class="input-group">
                                        <span class="input-group-btn">
                                            <span class="btn btn-default btn-file">
                                                <input name="fileimage" type="file" id="imgInp">
                                            </span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label>Sex</label>
                                    <select  name="sex" class="form-control" data-error="Please chose sex">
                                        <option value="1">Nam</option>
                                        <option value="0">Ná»¯</option>
                                    </select>
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="col-xs-4">
                                    <label>NgÃ y Sinh:</label>
                                    <div class="input-group date">
                                        <div class="input-group-addon"> <i class="fa fa-calendar"></i>
                                        </div>
                                        <input name="birthday" type="text" class="form-control pull-right" id="datepicker-birth-day" data-error="Please chose day" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <label>Tá»« tráº§n:</label>
                                    <div class="input-group date">
                                        <div class="input-group-addon"> <i class="fa fa-calendar"></i>
                                        </div>
                                        <input name="deadday" type="text" class="form-control pull-rFight" id="datepicker-dead-day">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <label >Äá»a Chá»:</label>
                                    <textarea type="text" name="address" class="form-control" placeholder="address"  ></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div id='message-form-create-forefather' class="col-sm-offset-2 col-sm-10"></div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-block btn-success">Create Forefather</button>
                                <button  data-dismiss="modal" class="btn btn-block btn-warning">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        
    </body>
</html>