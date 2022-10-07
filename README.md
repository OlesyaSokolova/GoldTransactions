# Тестовое задание для [SKYTEC GAMES](https://skytecgames.com/)

## 0. Исходные данные
Для реализации требуемой логики нужна информация о кланах, игроках и заданиях. Для них при инициализации базы данных создаются соответствующие таблицы. Также для присвоения пользователям заданий создана дополнительная таблица userstasks (см.файл [dbcreate.sql](https://github.com/OlesyaSokolova/GoldTransactions/blob/master/src/main/resources/dbcreate.sql)).

Перед началом игры класс [DataBaseInitializer](https://github.com/OlesyaSokolova/GoldTransactions/blob/master/src/main/java/com/skytecgames/testtask/sokolova/db/DataBaseInitializer.java) заполняет базу данных необходимой информацией: список кланов задается в [отдельном файле](https://github.com/OlesyaSokolova/GoldTransactions/blob/master/src/main/resources/clans.sql). Затем генерируются рандомные пользователи и задания. Тип задания может быть одним из перечисленных ниже:
```
public enum TaskType {
    FIGHT_WITH_ENEMY,
    FIND_ARTIFACT,
    CONQUERE_NEW_TERRITORY,
    EXPLORE_AREA
}
```

Каждому пользователю дается какое-то первоначальное количество золота. В казне каждого клана золота пока нет - она будет пополняться пользователями по мере выполнения заданий.


## 1. Подготовка к игре
После того, как в бд есть информация о кланах, игроках и возможных заданиях, класс [GameHost](https://github.com/OlesyaSokolova/GoldTransactions/blob/master/src/main/java/com/skytecgames/testtask/sokolova/GameHost.java) ("Ведущий")
 присваивает каждому пользователю задание (рандомно выбранное из таблицы):
 ```
 users = new ArrayList<>(userService.getAll());
        for (User user: users) {
            Task task = taskService.getRandom();
            user.assignTask(task);
            taskAssignementsService.save(user.getId(), task.getId());
        }
```
  
## 2. Процесс игры
Каждый пользователь начинает выполнять свое задание в отдельном потоке. Сложность задания прямо пропорциональна вознаграждению, которое обещано за его выполнение.  
После выполнения задания пользователь получает вознаграждение и отчисляет в казну клана какие-то проценты от всего своего (обновленного количества) золота.  
До и после выполнением задания засекается время.
```
public TransactionInfo performTask() {
        TransactionInfo transactionInfo = new TransactionInfo(id, clanId, task.getId());
        transactionInfo.setStartTime(LocalDateTime.now());

        for(int i = 0; i < task.getAward() * DIFFICULTY_COEFFICIENT; i++) {
            doSomethingVeryHard(i);
        }

        transactionInfo.setCompleteTime(LocalDateTime.now());
        gold += task.getAward();
        transactionInfo.setGoldDelta(gold * CLAN_CONTRIBUTION_PERCENTAGE/100);
        return transactionInfo;
    }
 ```
После того, как задание выполнено, казна клана, которому принадлежит игрок, пополняется заработанным им золотом.  
В приведенном ниже коде transactionInfo - краткая информация о транзакции: каким пользователем она была выполнена, какому клану он принадлежит, время начала и время завершеняи задания, сколько золота нужно зачислить клану. Эта информация дополняется: сколько золота было в клане, сколько надо добавить по результатам выполненного задания.   
DetailedTransactionInfo - вспомогательный класс для отображения информации о транзакции в удобном виде.  

 ```
 private synchronized DetailedTransactionInfo processTransactionResults(TransactionInfo transactionInfo) {
        int clanId = transactionInfo.getClanId();
        transactionInfo.setGoldBefore(clans[clanId].getGold());
        clans[clanId].updateGold(transactionInfo.getGoldDelta());
        try {
            clanService.update(clans[clanId]);
            DetailedTransactionInfo detailedTransactionInfo = getDetailedInfo(transactionInfo);

            //Информация о транзакция пишется в логи!
            log.info(detailedTransactionInfo);
          
            return detailedTransactionInfo;   
        } 
        .....
        .....
 ```    
 ## 3. Обработка резлуьтатов
Информация о транзакциях пишется в логи - для примера см. файл [transactions.log](https://github.com/OlesyaSokolova/GoldTransactions/transactions.log)

Более того, в результате игры формируется список сущностей с детальной информацией обо всех транзакциях. Их можно будет записать в текстовый файл, в excel-таблицу, или ещё как-то обработать.
```
List<DetailedTransactionInfo> transactionInfos  = completedTasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
```

## 4. Техничсекая информация
  - Java 11
  - MySQL
  - HikariCP
  - lombok
  - log4j-slf4j
  - commons-lang3
