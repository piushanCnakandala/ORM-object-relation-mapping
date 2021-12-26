package dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import entity.SuperEntity;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface SuperDAO<Entity extends SuperEntity,Id> {
    boolean add(Entity entity);
    boolean update(Entity entity);
    boolean delete(Id s);

    List<Entity> find();

}
