import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String city = "???";
        try (ServerSocket serverSocket = new ServerSocket(Config.PORT);) {
            System.out.println("Сервер запущен");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    out.println(city);
                    String citys = in.readLine();
                    if (city.charAt(city.length() - 1) == citys.charAt(0) || city.equals("???")) {
                        out.println("OK");
                        city = citys;
                    } else {
                        out.println("NOT OK");
                    }
                }
            }
        } catch (
                IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}