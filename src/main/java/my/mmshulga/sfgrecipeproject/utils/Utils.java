package my.mmshulga.sfgrecipeproject.utils;

public class Utils {
    public static Byte[] boxArrayOfBytes(byte[] unboxed) {
        int length = unboxed.length;
        Byte[] boxed = new Byte[length];
        for (int i = 0; i < length; i++) {
            boxed[i] = unboxed[i];
        }
        return boxed;
    }

    public static byte[] unboxArrayOfBytes(Byte[] boxed) {
        int length = boxed.length;
        byte[] unboxed = new byte[length];
        for (int i = 0; i < length; i++) {
            unboxed[i] = boxed[i];
        }
        return unboxed;
    }
}
