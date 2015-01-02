<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>

	</head>
	<body>
	<div class="row-fluid">
		<div class="span12">
		<h1>Список</h1>
		</div>
	</div>
	<div class="row-fluid">
		<ul class="list-group">
			<g:each in="${1..20}">
				<li class="list-group-item book-li-item">
					<img src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTTz0bEN_cVjUxGRoReN2ZwQmf5raxeNQwnuSZ0yhw4w5oT5jzTyQ"
							height="100px"/>
					Повесть о пионере Пете и о его друзьях: Лёше, Савве, Алисе, пожарнике Василии и двух студентах, приехавших к нам учиться из борющейся африканской республики, Мишеле и Андерсе.
				</li>
			</g:each>
		</ul>
	</div>
	</body>
</html>
