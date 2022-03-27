package task4;

public class Main {
    public static void main(String[] args) {
        System.out.println(crcCalculator("This is example text ...")); // 3473739588
    }

    static int crcCalculator(String text) {
        final int[] CRC_TABLE = new int[256];

        int crc = 0;
        for (int i = 0; i < 256; ++i) {
            int code = i;
            for (int j = 0; j < 8; ++j) {
                code = (code == 0x01 ? 0xEDB88320 ^ (code >>> 1) : (code >>> 1));
            }
            CRC_TABLE[i] = code;

            crc = -1;
            for (int j = 0; j < text.length(); ++j) {
                String codee = String.valueOf(text.charAt(j));
                crc = CRC_TABLE[(code ^ crc) & 0xFF] ^ (crc >>> 8);
            }
        }
        return (~crc);
    }
}


//const CRC_TABLE = Array(256);
//
//        for (let i = 0; i < 256; ++i) {
//        let code = i;
//        for (let j = 0; j < 8; ++j) {
//        code = (code & 0x01 ? 0xEDB88320 ^ (code >>> 1) : (code >>> 1));
//        }
//        CRC_TABLE[i] = code;
//        }
//
//        const crc32 = text => {
//        let crc = -1;
//        for (let i = 0; i < text.length; ++i) {
//        const code = text.charCodeAt(i);
//        crc = CRC_TABLE[(code ^ crc) & 0xFF] ^ (crc >>> 8);
//        }
//        return (-1 ^ crc) >>> 0;
//        };
//
//
//// Usage example:
//
//        console.log(crc32('This is example text ...'));  // 3473739588