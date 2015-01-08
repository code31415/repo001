<%@ page import="ru.repo001.PictureSize" %>
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
    <div class="col-xs-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-table"></i>
                    <span>Книги</span>
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
            <div class="box-content no-padding">
                <table class="table table-striped table-bordered table-hover table-heading no-border-bottom">
                    <thead>
                    <tr>
                        <th style="width: 50px;">#</th>
                        <th style="width: 70px;">Картинка</th>
                        <th>Название</th>
                        <th style="width: 20%;">Автор</th>
                        <th style="width: 50px">Рейтинг</th>
                        <th style="width: 50px;">Активна</th>
                        <th style="width: 150px;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${books}" var="book">
                    <tr>
                        <td>${book.id}</td>
                        <td>
                            <g:if test="${book.picture}">
                                <img src="${g.picture(picture: book.picture, size: PictureSize.SMALL)}" />
                            </g:if>
                        </td>
                        <td>${book.name.encodeAsHTML()}</td>
                        <td>${book.author.encodeAsHTML()}</td>
                        <td><g:formatNumber number="${book.rating}" maxFractionDigits="2" minFractionDigits="2"/></td>
                        <td>
                            <g:if test="${book.active}">
                                <i class="lbl-success fa fa-check-circle"></i>
                            </g:if>
                            <g:else>
                                <i class="lbl-danger fa fa-times-circle"></i>
                            </g:else>
                        </td>
                        <td>
                            <g:link action="edit" id="${book.id}" class="btn btn-default" type="button" href="#">Редактировать</g:link>
                        </td>
                    </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
