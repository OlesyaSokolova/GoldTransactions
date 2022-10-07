# Тестовое задание для [SKYTEC GAMES](https://skytecgames.com/)

## 0. Исходные данные
Для реализации требуемой логики требуется информация о кланах, игроках и заданиях. Для них создаются соответствующие таблицы. Также для присвоения пользователям заданий создана дополнительная таблица userstasks (см.файл [dbcreate.sql](https://github.com/OlesyaSokolova/GoldTransactions/blob/master/src/main/resources/dbcreate.sql)).

Перед началом игры класс [DataBaseInitializer](https://github.com/OlesyaSokolova/GoldTransactions/blob/master/src/main/java/com/skytecgames/testtask/sokolova/db/DataBaseInitializer.java) заполняет базу данных необходимой информацией: кланы задаются в [отдельном файле](https://github.com/OlesyaSokolova/GoldTransactions/blob/master/src/main/resources/clans.sql). Затем генерируются пользователи (с рандомными именами) и задания (рандомно выбирается тип задания: 
  
