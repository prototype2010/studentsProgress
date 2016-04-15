package database;

import constants.Constants;
import entity.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Boris on 18.02.2016.
 */
public class DataService implements Constants {
    private static List<DatabaseConnection> list = new LinkedList<DatabaseConnection>();

    public void init() {
        for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
            newConnection();
        }
    }

    private void newConnection() {
        DatabaseConnection conn = new DatabaseConnection(Constants.URL_TO_DATABASE);
        list.add(conn);
    }

    public List<Student> loadAllStudents() {

        DatabaseConnection conn = list.remove(0);
        List<Student> students = conn.loadAllStudents();
        list.add(conn);
        return students;
    }

    public List<Discipline> loadAllDisciplines() {
        DatabaseConnection conn = list.remove(0);
        List<Discipline> disciplines = conn.loadAllDisciplines();
        list.add(conn);
        return disciplines;

    }

    public List<Term> loadAllTerms() {
        DatabaseConnection conn = list.remove(0);
        List<Term> terms = conn.loadAllTerms();
        list.add(conn);
        return terms;

    }

    public void createStudent(Student student) {
        DatabaseConnection conn = list.remove(0);
        conn.createStudent(student);
        list.add(conn);
    }

    public void createDiscipline(Discipline discipline) {
        DatabaseConnection conn = list.remove(0);
        conn.createDiscipline(discipline);
        list.add(conn);
    }

    public List<Discipline> termDisciplinesList(int termId) {
        DatabaseConnection conn = list.remove(0);
        List<Discipline> disciplines = conn.termDisciplinesList(termId);
        list.add(conn);
        return disciplines;
    }

    public void newTermCreating(Term term, String[] disciplines) {
        DatabaseConnection conn = list.remove(0);
        conn.newTermCreating(term, disciplines);
        list.add(conn);
    }

    public void newTermBind(String disciplineName, int termId) {
        DatabaseConnection conn = list.remove(0);
        Discipline discipline = conn.getDisciplineID(disciplineName);
        conn.setNewTermBind(discipline.getId(), termId);
        list.add(conn);
    }

    public int getTermDuration(int termDuration) {
        DatabaseConnection conn = list.remove(0);
        Term term = conn.getTermDuration(termDuration);
        list.add(conn);
        return term.getDuration();

    }

    public void deleteStudent(int id) {
        DatabaseConnection conn = list.remove(0);
        conn.deleteStudent(id);
        list.add(conn);
    }

    public void deleteDiscipline(int id) {
        DatabaseConnection conn = list.remove(0);
        conn.deleteDiscipline(id);
        list.add(conn);
    }

    public void deleteTerm(int id) {
        DatabaseConnection conn = list.remove(0);
        conn.deleteTerm(id);
        list.add(conn);
    }

    public Student studentModifying(int id) {
        DatabaseConnection conn = list.remove(0);
        Student student = conn.studentModifying(id);
        list.add(conn);
        return student;
    }

    public void studentUpdate(Student student) {
        DatabaseConnection conn = list.remove(0);
        conn.studentUpdate(student);
        list.add(conn);
    }

    public Discipline disciplineModifying(int id) {
        DatabaseConnection conn = list.remove(0);
        Discipline discipline = conn.disciplineModifying(id);
        list.add(conn);
        return discipline;
    }

    public void disciplineUpdate(Discipline discipline) {
        DatabaseConnection conn = list.remove(0);
        conn.disciplineUpdate(discipline);
        list.add(conn);
    }
    public List<DisciplineMark> disciplineMarks(int studentId,int termId) {
        DatabaseConnection conn = list.remove(0);
        List<DisciplineMark> disciplineMarks=conn.disciplineMarks(studentId, termId);
        list.add(conn);
        return disciplineMarks;
    }
    public void setZeroStatusToDisciplines(Term term) {
        DatabaseConnection conn = list.remove(0);
        conn.setSetZeroStatusToDisciplines(term);
        list.add(conn);
    }
    public void bindModifiedTermDiscipline(String[] disciplines,Term term ) {
        DatabaseConnection conn = list.remove(0);
        conn.bindModifiedTermDisciplines(disciplines, term);
        list.add(conn);
    }
    public void updateTermDuration(int duration,int termId) {
        DatabaseConnection conn=list.remove(0);
        conn.updateTermDuration(duration, termId);
        list.add(conn);
    }
    public void rollBackDeletedTerm (int termId) {
        DatabaseConnection conn=list.remove(0);
        conn.rollbackDeletedTerm(termId);
        list.add(conn);
    }
    public List<LinkTermDiscipline> disciplinesByTermAndStudent (int termId,int studentId) {
        DatabaseConnection conn=list.remove(0);
        List<LinkTermDiscipline> resultList=conn.DisciplinesByTermAndStudent(termId, studentId);
        list.add(conn);
        return  resultList;

    }
    public  void setMark(List<LinkTermDiscipline> resultList,int studentId) {
        DatabaseConnection conn=list.remove(0);
        conn.setMark(resultList,studentId);
        list.add(conn);
    }
    public List<Role> loadAllRoles(){
        DatabaseConnection conn=list.remove(0);
        List<Role> resultList=conn.loadRoles();
        list.add(conn);
        return resultList;
    }
    public Account loginProcess(String login) {
        DatabaseConnection conn=list.remove(0);
        Account acc=conn.loginProcess(login);
        list.add(conn);
        return acc;

    }
    public Role getRole(int accId) {
        DatabaseConnection conn=list.remove(0);
        Role role=conn.roleByAccId(accId);
        list.add(conn);
        return role;

    }
}






