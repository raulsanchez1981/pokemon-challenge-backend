package challenge.services;

import challenge.enums.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TypeServiceImpl implements TypeService {


    @Override
    public List<String> obtainAllTypes() {
        return Stream.of(Type.values()).map(Type::getValue).collect(Collectors.toList());
    }
}
