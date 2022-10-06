package com.skytecgames.testtask.sokolova.db;

/*Public static Controller makeController() {
        DataSource dataSource = new DataSource(ConnectionPool.getInstance)
        ConferenceDao conferenceDao = new ConferenceDao(dataSource)
        SpeakerDao speakerDao = new SpeakerDao(dataSource)
        TalkDao talkDao = new TalkDao(dataSource);
        Return new Controller(conferenceDao, speakerDao, talkDao);
        }

        Дата сурс получает коннекшн пул (любым способом)
        Этот метод не делает ничего, но создает всю стойку с юнитами, втыкая в неё провода
        Уже внутри есть объекты-компоненты, он знает какие кому нужны. Внутри они инстанцируются и подпихиваются друг другу
        Это целая стойка, а на верхушке контроллер - в итоге получаем контроллер
        Нет недостатков синглтона:
        Можем тестировать*/

public class Controller {
}

