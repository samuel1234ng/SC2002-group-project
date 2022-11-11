import java.time.LocalDateTime;

public class TimeSlot {
//    private int timing;
    private Seat[][] seating = new Seat[10][16];

    public LocalDateTime dateTime;

//    private final String date;

    public TimeSlot(int timing, String date) {
        String time = Integer.toString(timing);
        int hour = Integer.parseInt(time.substring(0, time.length()-2));
        int minute = Integer.parseInt(time.substring(time.length()-2, time.length()-1));
        int year = Integer.parseInt(date.substring(6));
        int month = Integer.parseInt(date.substring(3, 5));
        int day = Integer.parseInt(date.substring(0, 2));
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
        char[] letters = {'*','I','H','G','F','E','D','C','B','A'};
        for (int i = 0; i < 10; i++) {
            char alpha = letters[i];
            for (int j = 0; j < 16; j++) {
                String num = Integer.toString(j + 1);
                String code = num + alpha;
                this.seating[i][j] = new Seat(code);
            }
        }
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public Seat[][] getSeats() {
        return this.seating;

    }

    public void setSeats(Seat[][] seating) {
        this.seating = seating;

    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getTime(){
        return this.dateTime.getHour()*100+this.dateTime.getMinute();
    }

    public String getDate(){
        return this.dateTime.getDayOfMonth()+"/"+this.dateTime.getMonthValue()+"/" + this.dateTime.getYear();
    }

    public void setSeat(int i, int j, boolean val){
		if(val){
			this.seating[i][j].assign();
		}else{
			this.seating[i][j].unassign();
		}
	}

    public void setTime(int time){
        String s_time = Integer.toString(time);
        int hour = Integer.parseInt(s_time.substring(0, s_time.length()-2));
        int minute = Integer.parseInt(s_time.substring(s_time.length()-2, s_time.length()-1));
        int year = this.dateTime.getYear();
        int month = this.dateTime.getMonthValue();
        int day = this.dateTime.getDayOfMonth();
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
    }
	
public int getDayType(){
	
	String dayType = this.dateTime.getDayOfWeek().name();
	if(dayType=="SATURDAY" || dayType=="SUNDAY"){
		return 1;
	}
	return 0;
	
}

}
