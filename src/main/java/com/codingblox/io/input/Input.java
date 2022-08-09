package com.codingblox.io.input;

import java.util.Scanner;

public interface Input {
    Input CONSOLE = () -> new Scanner(System.in).nextLine();

    String read();
}
