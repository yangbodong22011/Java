package MovieSystem.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiosk on 3/23/16.
 */
public class user {

    char[] username = new char[30];
    char[] userpasswd = new char[30];
    List<film> userFilm = new ArrayList<>();
    int userLeftMoney = 0;
}
