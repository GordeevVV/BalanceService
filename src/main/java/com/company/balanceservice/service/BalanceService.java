package com.company.balanceservice.service;

import java.util.Optional;

/**
 * Интерфейс сервиса для работы с банковским счётом содержит два метода
 **/
interface BalanceService {
    /**
     *  Получение баланса
     *
     *  @param id идентификатор банковского счёта
     *  @return сумма денег на банковском счёте
     */
    Optional<Long> getBalance(Long id);

    /**
     *  Изменение баланса на определённое значение
     *
     *  @param id идентификатор банковского счёта
     *  @param value сумма денег, которую нужно добавить к банковскому счёту
     */
    void changeBalance(Long id, Long value) throws InterruptedException;
}
