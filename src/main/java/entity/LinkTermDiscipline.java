package entity;

/**
 * Created by Boris on 18.02.2016.
 */
public class LinkTermDiscipline {

    private int id;
    private int idDiscipline;
    private int idTerm;
    private boolean status;
    private String disciplineName;
    private int mark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkTermDiscipline that = (LinkTermDiscipline) o;

        if (id != that.id) return false;
        if (idDiscipline != that.idDiscipline) return false;
        if (idTerm != that.idTerm) return false;
        if (status != that.status) return false;
        if (mark != that.mark) return false;
        return !(disciplineName != null ? !disciplineName.equals(that.disciplineName) : that.disciplineName != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idDiscipline;
        result = 31 * result + idTerm;
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (disciplineName != null ? disciplineName.hashCode() : 0);
        result = 31 * result + mark;
        return result;
    }

    @Override
    public String toString() {
        return "LinkTermDiscipline{" +
                "id=" + id +
                ", idDiscipline=" + idDiscipline +
                ", idTerm=" + idTerm +
                ", status=" + status +
                ", disciplineName='" + disciplineName + '\'' +
                ", mark=" + mark +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public int getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(int idTerm) {
        this.idTerm = idTerm;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
