package bjim.common;

import lombok.Value;

public class MessageParser {

    @Value
    public static class Message {
        String targetUsername;
        String msg;
        boolean selfMessage;
    }

    public static Message parse(String message) {
        int startOfTo = message.indexOf("to:");
        int endOfTo = message.indexOf(":\n", startOfTo + 3) + 3;
        String to = message.substring(startOfTo, endOfTo);
        String targetUser = to.split(":")[1];

        return new Message(
                targetUser, message.replaceFirst(to, ""), message.startsWith(targetUser));
    }
}
