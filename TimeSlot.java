public class TimeSlot {
    int timing;
    Seat[][] seating = new Seat[10][16];

    String date;

    public TimeSlot(int timing, String date) {

        char[] letters = {'*','I','H','G','F','E','D','C','B','A'};
        this.timing = timing;
        this.date = date;
        for (int i = 0; i < 10; i++) {
            char alpha = letters[i];
            for (int j = 0; j < 16; j++) {
                String num = Integer.toString(j + 1);
                String code = num + alpha;
                this.seating[i][j] = new Seat(code);
            }
        }
    }

    public Seat[][] getSeats() {
        return this.seating;

    }

    public void setSeats(Seat[][] seating) {
        this.seating = seating;

    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

	public void setSeat(int i, int j, boolean val){
		if(val){
			this.seating[i][j].assign();
		}else{
			this.seating[i][j].unassign();
		}
	}

}