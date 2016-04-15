package entity;

/**
 * Created by Boris on 04.03.2016.
 */
public class DisciplineMark {
    private String discipline;
    private int mark;
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DisciplineMark{" +
                "discipline='" + discipline + '\'' +
                ", mark=" + mark +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisciplineMark that = (DisciplineMark) o;

        if (mark != that.mark) return false;
        if (id != that.id) return false;
        return !(discipline != null ? !discipline.equals(that.discipline) : that.discipline != null);

    }

    @Override
    public int hashCode() {
        int result = discipline != null ? discipline.hashCode() : 0;
        result = 31 * result + mark;
        result = 31 * result + id;
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscipline() {

        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
