package exception;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException() {
        System.out.println("enter valid data");
    }
    public InvalidDataException(String string) {
        System.out.println("enter valid data");
    }
    public String toString(){
        return "given data not valid";

    }
}
