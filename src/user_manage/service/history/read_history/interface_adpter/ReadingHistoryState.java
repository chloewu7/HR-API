package user_manage.service.history.read_history.interface_adpter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ReadingHistoryState {
    private ArrayList<String> history;
    private String errorMessage;

    private String userName;

    public ReadingHistoryState() {
        this.history = new ArrayList<String>();
        this.errorMessage = "";
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setUserName(String userName){this.userName = userName;}

    public String getUserName(){return this.userName;}



}
