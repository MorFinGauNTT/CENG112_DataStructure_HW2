package CENG112_HW2_G04;

public class EmptyQueueException extends RuntimeException {

    public EmptyQueueException() {
        super("Queue is empty."); // Hata mesajını tanımlıyoruz
    }

    public EmptyQueueException(String message) {
        super(message); // Özel bir hata mesajı ile oluşturmak isteyebiliriz
    }
}
