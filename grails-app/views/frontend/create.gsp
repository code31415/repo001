<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="left-column">
    <div class="row-fluid">
        <div class="book-form">
            <h1>Добавление новой книги</h1>
            <br/>




            <g:form action="save" class="form-horizontal">


                <div class="well">

                    <div class="form-group has-error1">
                        <label for="name" class="col-sm-2 control-label">Название</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" placeholder="введите название">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label">Автор</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="author" placeholder="введите автора">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">

                            <button class="btn btn-primary" type="submit">Добавить книгу</button>
                        </div>
                    </div>
                </div>



            </g:form>
        </div>
    </div>
</div>
<div class="right-column">

</div>
<div class="clear"></div>
</body>
</html>
