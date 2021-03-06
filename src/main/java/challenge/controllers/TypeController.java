package challenge.controllers;

import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeServiceException;
import challenge.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(method= RequestMethod.GET, value="/search")
    public List<String> findPowers()  {
        try {
            return this.typeService.obtainAllTypes();
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

}
