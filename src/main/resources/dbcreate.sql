drop table if exists userstasks;
drop table if exists tasks;
drop table if exists users;
drop table if exists clans;

create table clans(
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(50),
      gold INT DEFAULT 0
);

create table users(
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(50),
      email VARCHAR(50), /*эта информация может пригодиться службе поддержки*/
      clanid INT not null,
      gold INT DEFAULT 50,
      FOREIGN KEY (clanid) REFERENCES clans(id)
);

create table tasks(
       id INT AUTO_INCREMENT PRIMARY KEY,
       type VARCHAR(50),
       description VARCHAR(255),
       award INT NOT NULL /*вознаграждение (золото) за выполнение задания*/
);

/*TODO:  вероятно, не нужна эта таблица, тк таблицы пользователей не будет
*//*таблица для реализации связи много-ко-многим: пользователь-задание */
create table userstasks (
        userid INT NOT NULL,
        taskid INT NOT NULL,
        PRIMARY KEY (userid, taskid),
        FOREIGN KEY (userid) REFERENCES users(id),
        FOREIGN KEY (taskid) REFERENCES tasks(id)
);

