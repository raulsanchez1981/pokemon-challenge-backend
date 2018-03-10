package challenge.services;

import challenge.enums.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TypeServiceTest {

    private List<String> enumList;

    @InjectMocks
    private TypeServiceImpl typeService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.enumList = Stream.of(Type.values()).map(Type::getValue).collect(Collectors.toList());

    }

    @Test
    public void testGetAllPokemons() {
        List<String> result = this.typeService.obtainAllTypes();
        Assert.assertEquals(result, this.enumList);
    }


}
