package MovieSystem.server;

/**
 * Created by kiosk on 3/23/16.
 */
public class film {
    char[] filmName = new char[30];           //电影名
    char[] filmIntroduce = new char[200];     //电影描述
    int  filmNumberOfHall = 0;                //演出厅号码
    char[] filmBeginTime = new char[20];      //电影开始时间
    char[] filmEndTime = new char[20];        //电影结束时间
    int[][] seat = new int[10][10];           //座位情况
    int filmMoney  = 0;                       //电影的票价

}
