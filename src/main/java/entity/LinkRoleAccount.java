package entity;

/**
 * Created by Boris on 18.02.2016.
 */
public class LinkRoleAccount {
    private int id;
    private int idRole;
    private int idAccount;
    private boolean status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkRoleAccount that = (LinkRoleAccount) o;

        if (id != that.id) return false;
        if (idRole != that.idRole) return false;
        if (idAccount != that.idAccount) return false;
        return status == that.status;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idRole;
        result = 31 * result + idAccount;
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }


    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LinkRoleAccount{" +
                "id=" + id +
                ", idRole=" + idRole +
                ", idAccount=" + idAccount +
                ", status=" + status +
                '}';
    }

}
