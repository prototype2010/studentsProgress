package entity;

/**
 * Created by Boris on 18.02.2016.
 */
public class Marks {
    private int id;
    private int disciplineId;
    private int studentId;
    private int mark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marks marks = (Marks) o;

        if (id != marks.id) return false;
        if (disciplineId != marks.disciplineId) return false;
        if (studentId != marks.studentId) return false;
        return mark == marks.mark;

    }

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", disciplineId=" + disciplineId +
                ", studentId=" + studentId +
                ", mark=" + mark +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + disciplineId;
        result = 31 * result + studentId;
        result = 31 * result + mark;
        return result;
    }
}
