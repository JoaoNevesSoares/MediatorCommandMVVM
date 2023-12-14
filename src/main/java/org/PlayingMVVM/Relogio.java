package org.PlayingMVVM;

public class Relogio {

    private int hour;
    private int minute;
    private int seconds;

    public Relogio(int hour, int minute, int seconds){
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }
    public void oneSecondPassed(){
        seconds++;
        if(seconds == 60){
            minute++;
            seconds = 0;
            if(minute == 60){
                hour++;
                minute = 0;
                if(hour == 24){
                    hour = 0;
                }
            }
        }

    }


}
