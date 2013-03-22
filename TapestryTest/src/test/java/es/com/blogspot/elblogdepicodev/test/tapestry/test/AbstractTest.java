package es.com.blogspot.elblogdepicodev.test.tapestry.test;

import com.formos.tapestry.testify.core.TapestryTester;
import com.formos.tapestry.testify.junit4.TapestryTest;

import es.com.blogspot.elblogdepicodev.test.tapestry.test.services.TestModule;


public abstract class AbstractTest extends TapestryTest {

    private static final TapestryTester SHARED_TESTER = new TapestryTester("es.com.blogspot.elblogdepicodev.test.tapestry", "app", "src/main/webapp", TestModule.class);

    public AbstractTest() {
        super(SHARED_TESTER);
    }    
}
