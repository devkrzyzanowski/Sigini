package pl.mkrzyzanowski.sigmacontroller.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Created by Michał on 2017-12-06.
 */

public class Tcp {
    private Socket socket;

    private boolean firstText = true;
    private int[] firstFrame;
    private int address;
    private int[] page;
    private int moveMethod;
    private int speed;
    private int[] color;
    private int font;
    //text
    private int[] newLine;
    private int endText;
    private int[] endMessage;

    private int[] testMessage;

    public Tcp(){

        firstFrame = new int[] {0x00, 0xFF, 0xFF, 0x00, 0x0B, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C,
                                0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E,
                                0x1F, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D, 0x2E, 0x2F, 0x30,
                                0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D, 0x3E, 0x3F, 0x40, 0x41, 0x42,
                                0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4A, 0x4B, 0x4C, 0x4D, 0x4E, 0x4F, 0x50, 0x51, 0x52, 0x53, 0x54,
                                0x55, 0x56, 0x57, 0x58, 0x59, 0x5A, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F, 0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66,
                                0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
                                0x79, 0x7A, 0x7B, 0x7C, 0x7D, 0x7E, 0x7F, 0xFF};
        address = 0x01;
        page = new int[] {0x30, 0x31};
        moveMethod = 0x01;
        //speed
        color = new int[] {0xEF, 0xB1};
        font = 0xA2;
        //text
        newLine = new int[] {0xEF, 0xB1, 0xEF, 0xA0, 0xFF};
        endText = 0xFF;
        endMessage = new int[] {0xFF, 0x00};

        testMessage = new int[] {0x0A, 0x01};

    }

    public Array<Integer> getData(int[] array){
        Array<Integer> message = new Array<Integer>();
        for (int a: array){
            message.add(a);
        }
        return message;
    }


    public void send(Array<Row> data) {
        Array<Integer> message = new Array<Integer>();

//        for (int aFirstFrame : firstFrame) {
//            message.add(aFirstFrame);
//        }

        message.addAll(getData(firstFrame));
        message.add(address);
//        for (int aPage : page) {
//            message.add(aPage);
//        }
        message.addAll(getData(page));

        for (Row r : data){
            if (!r.getTextField().getText().equals("")){
                byte[] bytes = r.getTextField().getText().getBytes();
                moveMethod = r.getMoveMethod() + 1;
                speed = r.getSpeed() + 192;
                font = r.getFont() + 160;
                Gdx.app.log("", String.valueOf(speed));
                if (firstText) {
                    firstText = false;
                } else {
                    for (int aNewLine : newLine){
                        message.add(aNewLine);
                    }
                }

                message.add(moveMethod);

                message.add(0xEF);
                message.add(speed);

                for (int aColor : color){
                    message.add(aColor);
                }

                message.add(0xEF);
                message.add(font);

                for (byte b : bytes){
                    message.add((int) b);
                }
            }
        }

        message.add(endText);

            for (int aEndMessage : endMessage) {
                message.add(aEndMessage);
            }
        sendTCP(message);
    }

    private void sendTCP(Array<Integer> message) {
        SocketHints socketHints = new SocketHints();
        socketHints.connectTimeout = 4000;
        try {
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "192.168.1.1", 9021, socketHints);
        } catch (GdxRuntimeException e){
            e.printStackTrace();
        }
        try {
            for (Integer t : message) {
                socket.getOutputStream().write(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
