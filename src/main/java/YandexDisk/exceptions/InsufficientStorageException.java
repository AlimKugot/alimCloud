/*
 * Лицензионное соглашение на использование набора средств разработки
 * «SDK Яндекс.Диска» доступно по адресу: http://legal.yandex.ru/sdk_agreement
 *
 */

package YandexDisk.exceptions;

import YandexDisk.exceptions.ServerWebdavException;

public class InsufficientStorageException extends ServerWebdavException {

    public InsufficientStorageException() {
        super("The server is unable to store the representation needed to complete the request");
    }

}
