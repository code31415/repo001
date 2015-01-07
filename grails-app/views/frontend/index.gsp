<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>

	</head>
	<body>
	<div class="left-column">
	<div class="row-fluid">
		<ul class="list-group">
			<g:each in="${1..20}" var="i">
				<li class="book-li-item">
					<span class="num">${i}</span>
					<div class="pic">
						<img src="http://www.mdk-arbat.ru/small-book-image.php?id=799733" height="90px"/>
					</div>
					<div class="info">
						<span class="name">
							<g:link action="show" controller="book">Республика ШКИД</g:link>
						</span>
						<span class="gray">
						Белых, Г. Г.
						</span>
					</div>
					<div class="clear"></div>
				</li>
			</g:each>
		</ul>
	</div>
	</div>
	</body>
</html>
