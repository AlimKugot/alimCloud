/*
 * Лицензионное соглашение на использование набора средств разработки
 * «SDK Яндекс.Диска» доступно по адресу: http://legal.yandex.ru/sdk_agreement
 *
 */

package YandexDisk;

public interface ProgressListener {
    void updateProgress(long loaded, long total);

    boolean hasCancelled();
}
