# WalkNavigator

Задание 1:

1. + Создать проект нового приложения "Прогулочный навигатор"

2. + Подключить Yandex Map Kit к своему проекту

3. + Добавить главный экран на котором показать Яндекс Карту

4. - без использования MVVM и ViewModel

Задание 2:

1. + Добавить панель внизу экрана, в которой поместить 1 текстовое поле "Финиш:" и кнопку "Начать", друг под другом

2. + По нажатию на текст-кнопку "Финиш:" открыть экран, с полем для ввода адреса в формате "Улица, 12"

3. + При вводе адреса показать список найденных адресов под полем ввода (использовать Yandex Map Kit)

4. + По нажатию на один из адресов в списке вернуться на главный экран и обновить текст на "Финиш: Улица, 12"

5. - без использования MVVM и ViewModel, и MVP

6. - без использования ConstraintLayout и Compose

Задание 3:

1. + По кнопке "Начать" - убрать панель с выбором финиша

2. + Показать на карте маршрут от координат  в которых находится пользователь, до координат финиша

3. + Добавить кнопку по которой карта будет центрироваться на координатах пользователя (смотрите Map Kit, должно быть что-то стандартное)

4. + Добавить на карту маркер, который будет перемещаться вслед за пользователем при смене координат

5. + Добавить кнопку "Стоп" внизу экрана, по нажатию на которую очищать карту и снова показывать панель выбора финиша

Задание 4:

1. + В режиме активной прогулки (когда показан маршрут и кнопка стоп), в правом верхнем углу показать кол-во сделанных шагов в формате "Шаги: 9136"

2. + Шаги подсчитывать с помощью акселерометра встроенного в устройство

3. + По нажатию кнопки "Стоп", шаги сбрасывать (задача в том чтобы при каждом старте прогулки начинать сначала)

4. + По нажатию на кнопку "Стоп" показать диалоговое окно с надписью "Вы прошли 9963 шагов! Хотите закончить прогулку?", и две кнопки "Да", "Нет". По кнопке "Нет" продолжаем прогулку. По кнопке "Да" выходим на главный экран.

Задание 5:

1. + Добавить боковую панель (Navigation Drawer)

2. + Вверху написать заголовок "Прогулочный навигатор"

3. + Ниже добавить текст: "Рекорд: 2000 шагов"

4. + При выходе из прогулки обновлять рекорд если пройдено больше чем раньше

5. + Восстанавливать рекорд при старте приложения
