
// -------------------------------------------------------------------------
/**
 *  Used for collecting experimental data about the two different
 *  queues
 *
 *  @author Jack Cob <jack3>
 *  @version Apr 11, 2014
 */
public class ExperimentTest extends student.TestCase
{
    //Fields
    private Experiment tester;

    /**
     * runs before every test and sets up the testing environment
     */
    public void setUp()
    {
        tester = new Experiment();
        tester.moveN(5);
    }

    /**
     * test method used to generate output for data about fifo. The assert is
     * just to make webcat happy about the test having at least one assert. I'm
     * using the tests to collect data about runtime and path size.
     */
    public void testFIFISol()
    {
        tester.testFIFO();

        assertNotNull(tester);
    }


    /**
     * test method used to generate output for data about the
     * pq. The assert is
     * just to make webcat happy about the test having at least one assert. I'm
     * using the tests to collect data about runtime and path size.
     */
    public void testPQSol()
    {
        System.out.println("PQ:");
        tester.testPQ();

        assertNotNull(tester);
    }

}
