# Базовая система для управления банковским аккаунтом

**Файл для конфигурации подключения к БД**
[Application.yaml](src/main/resources/application.yaml)



[BalanceController](src/main/java/com/company/balanceservice/controller/BalanceController.java)
Эндпоинты для управления сущностями класса [BankAccount](src/main/java/com/company/balanceservice/entity/BankAccount.java)

[BankAccountRepository](src/main/java/com/company/balanceservice/repository/BankAccountRepository.java)
Интерфейс для взаимодействий с б/д посредством Spring ORM

[BalanceService](src/main/java/com/company/balanceservice/service/BalanceServiceImpl.java)
Спецификация функционала репозитория

[Тесты](src/test/java/com/company/balanceservice)

[Конфигурация для тестов](src/test/resources/application-test.properties)