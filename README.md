Что я выбрал 
LazyList (LazyColumn & LazyRow) для андроид
Версия compose multiplatform - 1.7.0

ТестКейсы
	В папке testcases
	Там еще много что можно  добавить, пытался написать базу


Найденные баги
	Я не нашел багов, потому что не успел
	Много времени потратил на выбор приложения на котором тестить(хотя самое простое было спросить про это приложение, но я почему то сразу начал билдать экзамплы и смотреть на них)
	Единственное что можно считать багом - UI визард отдает не последнюю стейбл версию
	

Где еще нужно дополнительно поисследовать(как мне кажется)
	1)Несмотря на то что Note that Scrollbar is only available on desktop platforms
	Для андроида существует какой то аналог скрола LazyListScrollBar ?
	Я бы поизучал как правильно добавляют скрол(как вертикальный так и горизонтальный) и ломает ли это LazyList
	2)Очень не хватает для тестирования какого то такого приложения 
	https://medium.com/@gregkorossy/hacking-lazylist-in-android-jetpack-compose-38afacb3df67
	я бы еще сделал в нем 1000 записей
	не хватило времени чтобы его тспользовать
	3)Мне кажется большинство проблем буду совместном использовании компонентов, как это было у скролбаров на windows
	Но для проверки этого хорошее бизнес прилжение, которого я не смог найти(понять)
	Также нужно лучше проверить сохранение стейта внутри LazyList


Автоматизация
	LazyRowTest
	LazyColumnTest
	Не успел перенести в андроид секцию(много времени потратил не на ту инструкцию)
	Но общий вид тестов останется такой же что для десктопа что для андроида
	Почему использовал runComposeUiTest?
		Сначала мне показалось это логичным потому что это удобно
		Можем гибко настраивать компонент и нам не нужен другой репозиторий все можем делать тут же
		А потом я увидел что джетпак рекомендует использовать эспрессо
		И тут я засомневался, но времени уже не было
		На мой взгляд для написания тестов которые запускаются за один день способ подходит
		Написал только базовые сценарии из за недостатка времени, но они работают)
	Не нашел сходу а как легко резолвить текущий элемент LazyList(не перебираю все)
	
Где потерял время
	Локальный запуск
	Выбор подходящего приложения для тестирования
	И конечно же долго плутал в документации
	С непривычки тратил много времени на простых вещах
	
Проблемы не относящиеся напрямую к тестам
	1)AVD не хотел стартовать из за процессора AMD
	2)https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html - не совсем корректно на мой взгляд, не понятно как добавить тесты не только для десктопа
	3)Осутствие документации на методы compose multiplatform (документация Jetpack compose не совсем походит)