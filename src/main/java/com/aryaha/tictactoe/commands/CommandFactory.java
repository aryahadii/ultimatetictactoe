package com.aryaha.tictactoe.commands;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.aryaha.core.game.commands.ICommand;
import com.aryaha.tictactoe.messages.PlaceMessage;

public class CommandFactory {
    public static ICommand fromJsonString(String cmdString) throws Exception {
        JSONObject json = stringToJsonObject(cmdString);
        return makeRelatedCommand(json);
    }

    private static ICommand makeRelatedCommand(JSONObject obj) throws Exception {
        if (!(obj.get("command") instanceof String)) {
            throw new Exception("cannot parse command field");
        }

        switch ((String) obj.get("command")) {
            case MoveRequestCommand.COMMAND_TYPE:
                return makeMoveRequestCommand(obj);

            case PlaceCommand.COMMAND_TYPE:
                return makePlaceCommand(obj);

            case RoundUpdateCommand.COMMAND_TYPE:
                return makeRoundUpdateCommand(obj);

            case ServerSettingsCommand.COMMAND_TYPE:
                return makeServerSettingsCommand(obj);
        }
        return null;
    }

    private static MoveRequestCommand makeMoveRequestCommand(JSONObject obj) {
        // TODO
        return null;
    }

    private static PlaceCommand makePlaceCommand(JSONObject obj) {
        int x = (int)((long) obj.get("x"));
        int y = (int)((long) obj.get("y"));
        PlaceMessage msg = new PlaceMessage(x, y);
        return new PlaceCommand(msg);
    }

    private static RoundUpdateCommand makeRoundUpdateCommand(JSONObject obj) {
        // TODO
        return null;
    }

    private static ServerSettingsCommand makeServerSettingsCommand(JSONObject obj) {
        // TODO
        return null;
    }

    private static JSONObject stringToJsonObject(String str) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(str);
    }
}
