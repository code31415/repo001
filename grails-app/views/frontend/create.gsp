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
            <g:form action="save" class="form-horizontal">
                <div class="well">

                    <div class="form-group ${g.hasErrors(bean: book, field: 'name', 'has-error')}">
                        <label for="name" class="col-sm-2 control-label">Название</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name" placeholder="введите название"
                                   value="${g.fieldValue(bean: book, field: 'name')}"/>
                        </div>
                    </div>
                    <div class="form-group ${g.hasErrors(bean: book, field: 'author', 'has-error')}">
                        <label for="author" class="col-sm-2 control-label">Автор</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="author" name="author" placeholder="введите автора"
                                   value="${g.fieldValue(bean: book, field: 'author')}"/>
                        </div>
                    </div>

                    <div class="form-group ${captchaValid == false ? 'has-error': ''}">
                        <label for="captcha" class="col-sm-2 control-label">Текст с картинки</label>
                        <div class="col-sm-2">
                            <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control col-sm-2" id="captcha" name="captcha" placeholder="введите текст с картинки"
                                   value=""/>
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
