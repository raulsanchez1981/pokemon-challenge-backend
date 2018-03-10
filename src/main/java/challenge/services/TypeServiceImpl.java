package challenge.services;

import challenge.enums.Type;
import challenge.exception.types.ChallengeServiceException;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@EnableConfigurationProperties
public class TypeServiceImpl implements TypeService {


    @Autowired
    ErrorMessages errorMessages;


    @Override
    public List<String> obtainAllTypes() {
        try {
            return Stream.of(Type.values()).map(Type::getValue).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.FIND_TYPES_ERROR));
        }
    }
}
