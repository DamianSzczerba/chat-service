package com.chat;

public class Boot {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            args = new String[]{"server"}; // default
        }

        Template template = new Template();
        template.run(args);
    }
}