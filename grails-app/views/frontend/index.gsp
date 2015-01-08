<%@ page import="ru.repo001.PictureSize" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
	<div class="left-column">
	<div class="row-fluid">
		<ul class="list-group">
			<g:each in="${books}" var="book" status="i">
				<li class="book-li-item">
					<span class="num">${i+1}</span>
					<div class="pic">
						<g:if test="${book.picture}">
							<img src="${g.picture(picture: book.picture, size: ru.repo001.PictureSize.SMALL)}" />
						</g:if>
					</div>
					<div class="info">
						<span class="name">
							<g:link action="book" controller="frontend" id="${book.id}">${book.name.encodeAsHTML()}</g:link>
						</span>
						<span class="gray">
							${book.author.encodeAsHTML()}
						</span>
					</div>
					<div class="clear"></div>
				</li>
			</g:each>
		</ul>
	</div>
	</div>
	<div class="right-column">

	</div>
	<div class="clear"></div>
	</body>
</html>
