package model;

public class Age {
    private int years;
    private int months;
    public Age(){
        this(0,0);
    }
    public Age(int years, int months){
        this.months = months;
        this.years = years;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonths(){
        return months;
    }

    public int getYears() {
        return years;
    }

    @Override
    public String toString() {
        return years + " лет " +
                months + " месяцев\n";
    }
}
