package dao;

import dao.custom.impl.ProgramDAOImpl;
import dao.custom.impl.StudentDAOImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory() {
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case STUDENT :
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            default:
                return null;
        }
    }
    public enum DAOTypes{
        STUDENT,PROGRAM
    }
}
