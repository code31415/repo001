<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin"/>
</head>
<body>

<div class="row">
    <div class="col-xs-12" id="breadcrumb">
        <ol class="breadcrumb">
            <li><a href="index.html">Dashboard</a></li>
            <li><a href="#">Tables</a></li>
            <li><a href="#">Simple Tables</a></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>Registration form</span>
                </div>
                <div class="box-icons">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="expand-link">
                        <i class="fa fa-expand"></i>
                    </a>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
                <div class="no-move"></div>
            </div>
            <div class="box-content">
                <h4 class="page-header">Registration form</h4>
                <g:form role="form" class="form-horizontal" action="update" enctype="multipart/form-data">
                    <g:hiddenField name="id" value="${book.id}"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Название</label>
                        <div class="col-sm-4">
                            <input type="text"
                                   class="form-control"
                                   name="name"
                                   value="${g.fieldValue(bean: book, field: 'name')}"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Автор</label>
                        <div class="col-sm-4">
                            <input type="text"
                                   class="form-control"
                                   name="author"
                                   value="${g.fieldValue(bean: book, field: 'author')}"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Рейтинг</label>
                        <div class="col-sm-4">
                            <input type="text"
                                   class="form-control"
                                   name="rating"
                                   value="${g.fieldValue(bean: book, field: 'rating')}"
                            />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Активна</label>
                        <div class="col-sm-4">
                            <div class="checkbox">
                                <label>
                                    <g:checkBox name="active" checked="${book.active}"/>
                                    <i class="fa fa-square-o small"></i>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Картинка</label>
                        <div class="col-sm-4">
                            <input type="file"
                                   class="form-control"
                                   name="pictureFile"
                                   value=""
                            />
                        </div>
                    </div>

                    <div class="clearfix"></div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-4">
                            <g:link action="list" class="btn btn-default">
                                Отмена
                            </g:link>
                            <button class="btn btn-primary" type="submit">Сохранить</button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
