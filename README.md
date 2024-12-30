# Процедура запуска автотестов.
## ПО для запуска автотестов:

* IntelliJ IDEA 2024.2.5 (Ultimate Edition).
* Java Development Kit (JDK) 11.
* Docker Desktop.
## Шаги:
Открыть скопированный проект в IDEA, выполнить команды:

1.	docker-compose up   - для запуска контейнера c MySQL, с PostgreSQL и Эмулятором банковского сервиса.


2.	В терминале запустить приложение aqa-shop.jar:

*	Для тестирования запросов в БД MySQL команда java -jar ./artifacts/aqa-shop.jar
*	Для тестирования запросов в БД PostgreSQL
     
  java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/db 

-Dspring.datasource.username=app

-Dspring.datasource.password=pass -jar aqa-shop.jar. 

Возможно, в windows потребуется добавить кавычки: 

 java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/db"

"-Dspring.datasource.username=app" 

"-Dspring.datasource.password=pass" -jar aqa-shop.jar 

3. Для запуска автотестов:

* с проверкой БД MySQL в командной строке выполнить команду ./gradlew test

* с проверкой БД PostgreSQL  в командной строке выполнить команду  ./gradlew test -Ddb.url=jdbc:postgresql://localhost:5432/db.


4. Для получения отчета по результатам прогона автотестов дважды нажать ctrl выполнить команду gradle allureServe, отчет откроется в браузере.
