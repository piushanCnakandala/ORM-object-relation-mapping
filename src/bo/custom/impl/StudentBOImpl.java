package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
  StudentBOImpl studentBO= (StudentBOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean add(StudentDTO studentDTO) {
        return studentBO.add(new StudentDTO(
                studentDTO.getRegNo(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNo(),
                studentDTO.getAddress(),
                studentDTO.getEmail(),
                studentDTO.getGender()
        ));
    }

    @Override
    public List<StudentDTO> find() {
        List<StudentDTO> list = studentBO.find();
        ArrayList<StudentDTO>  dtoArrayList= new ArrayList<>();
        StudentDTO studentDTO=null;
        for (StudentDTO student:list){
            dtoArrayList.add(new StudentDTO(
                    student.getRegNo(),
                    student.getName(),
                    student.getAge(),
                    student.getContactNo(),
                    student.getAddress(),
                    student.getEmail(),
                    student.getGender()
            ));
        }
        return dtoArrayList;
    }

    @Override
    public boolean delete(String id) {
        return studentBO.delete(id);
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return studentBO.update(new StudentDTO(
                studentDTO.getRegNo(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNo(),
                studentDTO.getAddress(),
                studentDTO.getEmail(),
                studentDTO.getGender()
        ));
    }
}
