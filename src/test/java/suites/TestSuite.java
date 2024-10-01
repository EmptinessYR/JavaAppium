package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)

@Suite.SuiteClasses ({
        ArticleTests.class,
        ChangeAppCondition.class,
        GetStartedTest.class,
        SavedLists.class,
        SearchTests.class
})

public class TestSuite {
}
