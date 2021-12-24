package dao.custom.impl;

import Util.FactoryConfiguration;
import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.custom.ProgramDAO;
import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean add(Program entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(ID s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program program = session.get(Program.class, s);
        session.delete(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Program> find() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Program");

        List<Program> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}