package firstgrails;

import java.util.List;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

public class CardReaderSystem {

    private static Card card = null;
    private static CardChannel channel = null;
    public static byte[] MY_SERVICE_AID = { (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x12, (byte) 0x34, (byte) 0x56};
    public static byte[] SELECT_APDU_HEADER = {0x00, (byte) 0xA4, 0x00, 0x04};
//    public static byte[] SELECT_APDU = {SELECT_APDU_HEADER, 0x08, MY_SERVICE_AID, 0x00};
    public static byte[] myCmd = { (byte) 0xFF, (byte) 0xCA, 0x00, 0x00, 0x00};
    /**
     * @param args
     */
    public static void main(String[] args) {
        executeCommand(myCmd);
    }
    private static void executeCommand(byte[] apdu) {

        try {
            if (openConnection()) {
                byte []rep = null;
                ATR atr = card.getATR() ;
                rep = atr.getBytes() ;
                try {
                    System.out.println("ATR: "+ byteArrayToHexString(rep));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (channel!=null) {
                    try {
                        System.out.println("Command: "+ byteArrayToHexString(apdu));
                        apdu = sendCommand(apdu, channel);
                        if (apdu!=null)
                            System.out.println("Response: "+ byteArrayToHexString(apdu));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean openConnection() {
        TerminalFactory factory = TerminalFactory.getDefault();
        CardTerminals cardterminals = factory.terminals();
        card = null;
        try {
            List<CardTerminal> terminals = cardterminals.list();
            System.out.println("Terminals: " + terminals);
            CardTerminal terminal = cardterminals.getTerminal(terminals.get(0).getName());
            terminal.waitForCardPresent(20000);
            System.out.println("Card detected!");
            card = terminal.connect("*");
            channel = card.getBasicChannel();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static void disconnect() {
        try {
            channel.close();
            card.disconnect(false);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public static byte[] sendCommand(byte[] cmd, CardChannel channel) throws Exception {
        byte []rep = null;
        ResponseAPDU r = null;
        try {
            CommandAPDU apdu = new CommandAPDU(cmd);
            try {
                System.out.println("APDU Command: " + byteArrayToHexString(apdu.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            r = channel.transmit(apdu);
            rep = r.getBytes();
            try {
                System.out.println("APDU Response: " + byteArrayToHexString(rep));
                System.out.println("Data : " + byteArrayToHexString(r.getData()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rep;
        } catch (CardException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String byteArrayToHexString(byte[] bArray) throws Exception
    {
        if (bArray.length==0)
        {
            throw new Exception();
        }
        StringBuilder sb = new StringBuilder(bArray.length * 2);
        for (byte b : bArray)
        {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

}
