package entity;

/**
 * Created by Boris on 18.02.2016.
 */
public class Term {
    private int id;
    private int term;
    private int duration;
    private boolean status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term1 = (Term) o;

        if (id != term1.id) return false;
        if (term != term1.term) return false;
        if (duration != term1.duration) return false;
        return status == term1.status;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + term;
        result = 31 * result + duration;
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Term{" +

                "id=" + id +
                ", term=" + term +
                ", duration=" + duration +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

