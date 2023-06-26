# Процедура запуска автотестов

1. Если на компьютере не установлен Docker, необходимо его установить согласно [инструкции](https://github.com/netology-code/aqa-homeworks/blob/b5e33552ddae8429b7be2260d00a7bdb48c21f35/docker/installation.md) .
2. Для поднятия вспомогательных сервисов выполнить docker-compose up
3. Запустить SUT с помощью команды в терминале java -jar aqa-shop.jar 
4. Запустить тесты: нажать дважды Ctrl, в появившейся строке ввести gradle test clean
5. Сформировать отчет в репорт-системе Allure с помощью команды в терминале ./gradlew allureReport (найти в проекте папку report, в ней файл html, открыть его с помощью браузера).
6. Для подключения СУБД PostgreSQL в терминале остановить работу -jar файла (Ctrl+C), далее ввести команду в терминал java -D'spring.datasource.url=jdbc:postgresql://localhost:5432/app' -jar aqa-shop.jar 
7. Для запуска тестов с СУБД PostgreSQL в терминале указываем DATASOURCE_URL=jdbc:postgresql://localhost:5432/app ./gradlew clean test (где DATASOURCE_URL - переменная окружения).
8. Формирование отчета с данной СУБД аналогично пункту 5.
