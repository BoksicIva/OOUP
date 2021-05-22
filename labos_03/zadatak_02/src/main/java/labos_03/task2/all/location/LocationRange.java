package labos_03.task2.all.location;

import java.util.Objects;

public class LocationRange {
    private Location start;
    private Location end;

    public LocationRange(Location start, Location end){
        this.start=start;
        this.end=end;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public void replace(LocationRange range){
        if (range.getStart().getRow() > range.getEnd().getRow()) {
            int temp1 = range.getStart().getColumn();
            int temp2 = range.getStart().getRow();

            range.setStart(new Location(range.getEnd()));
            range.setEnd(new Location(temp2, temp1));
        } else if (range.getStart().getRow() == range.getEnd().getRow() && range.getStart().getColumn() > range.getEnd().getColumn()) {
            int temp1 = range.getStart().getColumn();
            int temp2 = range.getStart().getRow();

            range.setStart(new Location(range.getEnd()));
            range.setEnd(new Location(temp2, temp1));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationRange that = (LocationRange) o;
        return start.equals(that.start) && end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "LocationRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
