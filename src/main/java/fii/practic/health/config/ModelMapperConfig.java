package fii.practic.health.config;

import fii.practic.health.boundry.dto.PatientDTO;
import fii.practic.health.entity.model.Patient;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(PatientDTO.class, Patient.class).addMappings(m ->{
            m.<Long>map(PatientDTO::getDoctorId, (Patient, v) -> Patient.getDoctor().setDoctorId(v));
        });

        modelMapper.typeMap(Patient.class, PatientDTO.class).addMappings(m ->{
            m.map(src -> src.getDoctor().getDoctorId(), PatientDTO::setDoctorId);
        });

        return modelMapper;
    }
}