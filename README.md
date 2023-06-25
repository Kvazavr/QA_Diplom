# Процедура запуска автотестов

1. Если на компьютере не установлен Docker, необходимо его установить согласно [инструкции](https://github.com/netology-code/aqa-homeworks/blob/b5e33552ddae8429b7be2260d00a7bdb48c21f35/docker/installation.md) 
2. Для поднятия вспомогательных сервисов выполнить docker-compose up
3. Запустить SUT с помощью команды в терминале java -jar aqa-shop.jar 
4. java -Xmx64m -D'spring.datasource.url=jdbc:postgresql://localhost:5432/app' -jar aqa-shop.jar 
5. Запуск тестов DATASOURCE_URL=jdbc:postgresql://localhost:5432/app ./gradlew clean test
