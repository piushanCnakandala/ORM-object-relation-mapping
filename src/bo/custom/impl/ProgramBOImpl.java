package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dto.ProgramDTO;
import dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramBOImpl programBO= (ProgramBOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    @Override
    public boolean add(ProgramDTO programDTO) {
        return programBO.add(new ProgramDTO(
                programDTO.getProgramId(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }

    @Override
    public List<ProgramDTO> find() {
        List<ProgramDTO> list = programBO.find();
        ArrayList<ProgramDTO> dtoArrayList= new ArrayList<>();
        ProgramDTO programDTO=null;

        for (ProgramDTO program:list){
            dtoArrayList.add(new ProgramDTO(
                    program.getProgramId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()
            ));
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return programBO.delete(id);
    }

    @Override
    public boolean update(ProgramDTO programDTO) {
        return programBO.update(new ProgramDTO(
                programDTO.getProgramId(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }
}
