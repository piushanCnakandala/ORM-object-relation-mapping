package dao.custom;

import dao.SuperDAO;
import entity.Program;

import java.util.List;

public interface ProgramDAO extends SuperDAO<Program,String> {
    List<Program> searchPrograms(String value);
}
