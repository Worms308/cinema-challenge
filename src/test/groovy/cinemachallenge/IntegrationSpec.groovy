package cinemachallenge

import com.worms308.cinemachallenge.CinemaChallengeApplication
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@TypeChecked
@SpringBootTest(classes = [CinemaChallengeApplication, IntegrationSpecConfiguration])
@ActiveProfiles(["integration"])
@Rollback
@AutoConfigureMockMvc
class IntegrationSpec extends Specification {

    @Autowired
    MockMvc mockMvc
}
