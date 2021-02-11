/*
 * Лицензионное соглашение на использование набора средств разработки
 * «SDK Яндекс.Диска» доступно по адресу: http://legal.yandex.ru/sdk_agreement
 *
 */

package YandexDisk.exceptions;

public class UnknownServerWebdavException extends WebdavException {
    public UnknownServerWebdavException(String message) {
        super(message);
    }
}
