package challenge.controllers;

import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeServiceException;
import challenge.services.TypeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


public class TypeControllerTest {

    private List<String> emptyList;

    @Mock
    private TypeService typeService;

    @InjectMocks
    private TypeController typeController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.emptyList = new ArrayList<>();
    }

    @Test
    public void testGetAllTypes() {
        Mockito.when(this.typeService.obtainAllTypes()).thenReturn(this.emptyList);
        List<String> result = this.typeController.findPowers();
        Assert.assertEquals(result, this.emptyList);
    }

    @Test(expected = ChallengeControllerException.class)
    public void testGetAllTypesException() {
        Mockito.when(this.typeService.obtainAllTypes()).thenThrow(new ChallengeServiceException("error", new Throwable()));
        this.typeController.findPowers();
    }

}
