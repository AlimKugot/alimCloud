/*
 * Лицензионное соглашение на использование набора средств разработки
 * «SDK Яндекс.Диска» доступно по адресу: http://legal.yandex.ru/sdk_agreement
 *
 */

package YandexDisk.exceptions;

public class UnsupportedMediaTypeException extends WebdavException {
    public UnsupportedMediaTypeException(String message) {
        super(message);
    }
}
