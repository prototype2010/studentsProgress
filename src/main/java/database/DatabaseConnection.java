package database;

import constants.Constants;
import entity.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Boris on 18.02.2016.
 */
public class DatabaseConnection implements Constants {

    private Connection conn;
    private ResultSet rs;
    private static PreparedStatement loadAllStudents;
    private static PreparedStatement loadAllDisciplines;
    private static PreparedStatement loadAllTerms;
    private static PreparedStatement createNewStudent;
    private static PreparedStatement createNewDiscipline;
    private static PreparedStatement termDisciplinesList;
    private static PreparedStatement createNewTerm;
    private static PreparedStatement newTermBind;
    private static PreparedStatement getDisciplineId;
    private static PreparedStatement getTermLength;
    private static PreparedStatement deleteStudent;
    private static PreparedStatement deleteDiscipline;
    private static PreparedStatement deleteTerm;
    private static PreparedStatement studentModifying;
    private static PreparedStatement studentUpdate;
    private static PreparedStatement disciplineModifying;
    private static PreparedStatement disciplineUpdate;
    private static PreparedStatement disciplineMarkTerm;
    private static PreparedStatement setZeroStatusToDisciplines;
    private static PreparedStatement updateTermDuration;
    private static PreparedStatement rollbackDeletedTerm;
    private static PreparedStatement disciplinesByTerm;
    private static PreparedStatement disciplineNameById;
    private static PreparedStatement markLinkTermDisciplineAndStudentId;
    private static PreparedStatement setMark;
    private static PreparedStatement updateMark;
    private static PreparedStatement loadRoles;
    private static PreparedStatement loginProcess;
    private static PreparedStatement roleByAccId;
    private static PreparedStatement getRole;

    public DatabaseConnection(String urlToDatabase) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL_TO_DATABASE);
            loadPrepearedStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadPrepearedStatement() {
        try {
            loadAllStudents = conn.prepareStatement("SELECT * FROM student WHERE status=1");
            loadAllDisciplines = conn.prepareStatement("SELECT * FROM discipline WHERE status=1");
            loadAllTerms = conn.prepareStatement("SELECT * FROM term WHERE status=1");
            createNewStudent = conn.prepareStatement("INSERT INTO student (name, surname, groupId, date) VALUES (?, ?, ?,?);");
            createNewDiscipline = conn.prepareStatement("INSERT INTO discipline (name) VALUES (?)");
            termDisciplinesList = conn.prepareStatement("SELECT * FROM link_term_discipline l join discipline d where l.id_term=? AND l.id_discipline=d.id AND d.status=1 AND l.status=1;");
            createNewTerm = conn.prepareStatement("INSERT INTO term (id,duration) VALUES ( ?, ? );");
            newTermBind = conn.prepareStatement("INSERT INTO link_term_discipline (id_discipline,id_term) VALUES  (?,?)");
            getDisciplineId = conn.prepareStatement("SELECT id FROM discipline WHERE name=?");
            getTermLength = conn.prepareStatement("SELECT duration FROM term WHERE id=?");
            deleteStudent = conn.prepareStatement("UPDATE student SET status=0 WHERE id=?");
            deleteDiscipline = conn.prepareStatement("UPDATE discipline SET status=0 WHERE id=?");
            deleteTerm = conn.prepareStatement("UPDATE term SET status=0 where id=?");
            studentModifying = conn.prepareStatement("SELECT * FROM student WHERE id=?");
            studentUpdate = conn.prepareStatement("UPDATE student SET name=?, surname=?,groupId=?,date=? WHERE id=?");
            disciplineModifying = conn.prepareStatement("SELECT * FROM discipline where id=?");
            disciplineUpdate = conn.prepareStatement("UPDATE discipline SET name=? WHERE id=?");
            disciplineMarkTerm = conn.prepareStatement("select * from marks m JOIN link_term_discipline l ON m.student_id=? and l.id_term=? and m.term_discipline_id=l.id; ");
            setZeroStatusToDisciplines = conn.prepareStatement("UPDATE link_term_discipline SET status=0 WHERE id_term=?");
            updateTermDuration = conn.prepareStatement("UPDATE term SET duration=? WHERE id=?");
            rollbackDeletedTerm = conn.prepareStatement("UPDATE term SET status=1 WHERE id=?");
            disciplinesByTerm = conn.prepareStatement("SELECT * FROM link_term_discipline WHERE id_term=? AND status=1");
            disciplineNameById = conn.prepareStatement("SELECT name,status FROM discipline where id=? ");
            markLinkTermDisciplineAndStudentId = conn.prepareStatement("SELECT * FROM marks WHERE term_discipline_id=? AND student_id=?");
            setMark = conn.prepareStatement("INSERT INTO marks (term_discipline_id,student_id,mark) VALUES (?,?,?)");
            updateMark = conn.prepareStatement("UPDATE marks SET mark=? WHERE term_discipline_id=? AND student_id=?");
            loadRoles = conn.prepareStatement("SELECT * FROM role WHERE status=1");
            loginProcess = conn.prepareStatement("SELECT * FROM account WHERE login=? AND status=1");
            roleByAccId = conn.prepareStatement("SELECT * FROM link_role_account WHERE id_account=? AND status=1");
            getRole = conn.prepareStatement("SELECT * FROM role WHERE id=? AND status=1");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> loadAllStudents() {
        rs = null;
        List<Student> students = new LinkedList<Student>();
        try {
            rs = loadAllStudents.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroupId(rs.getString("groupId"));
                student.setDate(rs.getDate("date"));
                if (rs.getInt("status") == 1) {
                    student.setStatus(true);
                } else student.setStatus(false);

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Discipline> loadAllDisciplines() {
        rs = null;
        List<Discipline> disciplines = new LinkedList<Discipline>();
        try {
            rs = loadAllDisciplines.executeQuery();
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setName(rs.getString("name"));
                if (rs.getInt("status") == 0) {
                    discipline.setStatus(false);
                } else discipline.setStatus(true);
                disciplines.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    public List<Term> loadAllTerms() {
        rs = null;
        List<Term> terms = new LinkedList<Term>();
        try {
            rs = loadAllTerms.executeQuery();
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setTerm(rs.getInt("term"));
                if (rs.getInt("status") == 0) {
                    term.setStatus(false);
                } else term.setStatus(true);
                terms.add(term);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return terms;
    }

    public void createStudent(Student student) {
        try {
            PreparedStatement query = createNewStudent;
            query.setString(1, student.getName());
            query.setString(2, student.getSurname());
            query.setString(3, student.getGroupId());
            query.setDate(4, student.getDate());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDiscipline(Discipline discipline) {
        try {
            PreparedStatement query = createNewDiscipline;
            query.setString(1, discipline.getName());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Discipline> termDisciplinesList(int termID) {
        rs = null;
        List<Discipline> list = new LinkedList<Discipline>();
        try {
            PreparedStatement query = termDisciplinesList;
            query.setInt(1, termID);
            rs = termDisciplinesList.executeQuery();
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setName(rs.getString("name"));
                list.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void newTermCreating(Term term, String[] disciplines) {
        try {
            PreparedStatement query = createNewTerm;
            query.setInt(1, term.getId());
            query.setInt(2, term.getDuration());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Discipline getDisciplineID(String disciplineName) {
        Discipline discipline = new Discipline();
        try {
            PreparedStatement query = getDisciplineId;
            query.setString(1, disciplineName);
            rs = getDisciplineId.executeQuery();
            while (rs.next()) {
                discipline.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    public void setNewTermBind(int disciplineId, int termId) {
        try {
            PreparedStatement query = newTermBind;
            query.setInt(1, disciplineId);
            query.setInt(2, termId);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Term getTermDuration(int termDuration) {
        Term term = new Term();
        try {
            PreparedStatement query = getTermLength;
            query.setInt(1, termDuration);
            rs = query.executeQuery();
            while (rs.next()) {
                term.setDuration(rs.getInt("duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return term;
    }

    public void deleteStudent(int id) {
        try {
            PreparedStatement query = deleteStudent;
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDiscipline(int id) {
        try {
            PreparedStatement query = deleteDiscipline;
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTerm(int id) {
        try {
            PreparedStatement query = deleteTerm;
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student studentModifying(int id) {
        Student student = new Student();
        try {
            PreparedStatement query = studentModifying;
            query.setInt(1, id);
            rs = query.executeQuery();
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroupId(rs.getString("groupId"));
                student.setDate(rs.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void studentUpdate(Student student) {
        PreparedStatement query = studentUpdate;
        try {
            query.setString(1, student.getName());
            query.setString(2, student.getSurname());
            query.setString(3, student.getGroupId());
            query.setDate(4, student.getDate());
            query.setInt(5, student.getId());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Discipline disciplineModifying(int id) {
        Discipline discipline = new Discipline();
        try {
            PreparedStatement query = disciplineModifying;
            query.setInt(1, id);
            rs = query.executeQuery();

            while (rs.next()) {
                discipline.setName(rs.getString("name"));
                discipline.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    public void disciplineUpdate(Discipline discipline) {
        PreparedStatement query = disciplineUpdate;
        try {
            query.setString(1, discipline.getName());
            query.setInt(2, discipline.getId());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DisciplineMark> disciplineMarks(int studentId, int termId) {
        List<DisciplineMark> list = new LinkedList<DisciplineMark>();
        PreparedStatement query = disciplineMarkTerm;
        try {
            query.setInt(1, studentId);
            query.setInt(2, termId);
            rs = query.executeQuery();
            while (rs.next()) {
                DisciplineMark disciplineMark = new DisciplineMark();
                disciplineMark.setId(rs.getInt("id_discipline"));
                disciplineMark.setMark(rs.getInt("mark"));
                list.add(disciplineMark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Discipline> disciplines = loadAllDisciplines();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < disciplines.size(); j++) {
                if (list.get(i).getId() == disciplines.get(j).getId()) {
                    list.get(i).setDiscipline(disciplines.get(j).getName());
                    continue;
                }
            }
        }
        // FIX FOR NULLS
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDiscipline() == null) {
                list.remove(i);
            }
        }

        return list;
    }

    public void setSetZeroStatusToDisciplines(Term term) {
        PreparedStatement query = setZeroStatusToDisciplines;
        try {
            query.setInt(1, term.getId());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bindModifiedTermDisciplines(String[] disciplines, Term term) {
        Discipline discipline = new Discipline();
        try {
            for (int i = 0; i < disciplines.length; i++) {
                PreparedStatement query = getDisciplineId;
                query.setString(1, disciplines[i]);
                rs = query.executeQuery();
                while (rs.next()) {
                    discipline.setId(rs.getInt("id"));
                }
                discipline.setName(disciplines[i]);
                PreparedStatement queryNewTerm = newTermBind;
                queryNewTerm.setInt(1, discipline.getId());
                queryNewTerm.setInt(2, term.getId());
                queryNewTerm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTermDuration(int duration, int termId) {
        PreparedStatement query = updateTermDuration;
        try {
            query.setInt(1, duration);
            query.setInt(2, termId);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackDeletedTerm(int termId) {
        PreparedStatement query = rollbackDeletedTerm;
        try {
            query.setInt(1, termId);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LinkTermDiscipline> DisciplinesByTermAndStudent(int termId, int studentId) {
        List<LinkTermDiscipline> list = new LinkedList<LinkTermDiscipline>();
        try {
            PreparedStatement query = disciplinesByTerm;
            query.setInt(1, termId);
            rs = query.executeQuery();
            while (rs.next()) {
                LinkTermDiscipline ltd = new LinkTermDiscipline();
                ltd.setId(rs.getInt("id"));
                ltd.setIdDiscipline(rs.getInt("id_discipline"));
                ltd.setIdTerm(rs.getInt("id_term"));
                list.add(ltd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
           try {
                PreparedStatement query = disciplineNameById;
                query.setInt(1, list.get(i).getIdDiscipline());
                rs = query.executeQuery();
                while (rs.next()) {
                    ///FIX FOR TERMS
                    if (rs.getInt("status")==1) {
                        list.get(i).setDisciplineName(rs.getString("name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            try {
                PreparedStatement query = markLinkTermDisciplineAndStudentId;
                query.setInt(1, list.get(i).getId());
                query.setInt(2, studentId);
                rs = query.executeQuery();
                while (rs.next()) {
                    list.get(i).setMark(rs.getInt("mark"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
/// FIXING NULLS
        List<LinkTermDiscipline> fixList=new LinkedList<LinkTermDiscipline>();
        for (int i=0,j=list.size();i<j;i++) {
            if(list.get(i).getDisciplineName()!=null) {
                fixList.add(list.get(i));
            }
        }

        return fixList;
    }


    public void setMark(List<LinkTermDiscipline> list, int studentId) {
        for (int i = 0; i < list.size(); i++) {
            try {
                PreparedStatement query = setMark;
                query.setInt(1, list.get(i).getId());
                query.setInt(2, studentId);
                query.setInt(3, list.get(i).getMark());
                query.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement query = updateMark;
            try {
                query.setInt(1, list.get(i).getMark());
                query.setInt(2, list.get(i).getId());
                query.setInt(3, studentId);
                query.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public List<Role> loadRoles() {
        List<Role> list = new LinkedList<Role>();
        try {
            PreparedStatement query = loadRoles;
            rs = query.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setName(rs.getString("name"));
                role.setId(rs.getInt("id"));
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Account loginProcess(String login) {
        Account acc = new Account();
        try {
            PreparedStatement query = loginProcess;
            query.setString(1, login);
            rs = query.executeQuery();
            while (rs.next()) {
                acc.setLogin(login);
                acc.setPassword(rs.getString("password"));
                acc.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }

    public Role roleByAccId(int accId) {
        Role role = new Role();
        int roleId = 0;
        try {
            PreparedStatement query = roleByAccId;
            query.setInt(1, accId);
            rs = query.executeQuery();
            while (rs.next()) {
                roleId = rs.getInt("id_role");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement query = getRole;
            query.setInt(1, roleId);
            rs = query.executeQuery();
            while (rs.next()) {
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}









