<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><g:layoutTitle default="Рейтинг детских книг"/></title>

	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">

		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div class="header">
			<h1>
				Рейтинг детских книг
			</h1>
			<ul class="menu">
				<li>
					<g:link controller="frontend" action="index">Рейтинг</g:link>
				</li>
				<li>
					<a href="#">Добавить книгу</a>
				</li>
				<li>
					<a href="#">О проекте</a>
				</li>
			</ul>
		</div>
		<div class="container-narrow">
			<g:layoutBody/>

			%{--<div class="footer" role="contentinfo"></div>--}%
		</div>


		<r:layoutResources />


		<script src="${resource(dir: 'js', file: 'jquery-1.11.1.min.js')}"></script>
		<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>
	</body>
</html>
