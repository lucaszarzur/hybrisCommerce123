package concerttours.jobs;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import java.lang.InterruptedException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import de.hybris.platform.core.Registry;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import concerttours.model.NewsModel;

@IntegrationTest
public class SendNewsJobIntegrationTest extends ServicelayerTransactionalTest
{
    @Resource
    private ModelService modelService;

    @Resource
    private SendNewsJob sendNewsJob;

    @Before
    public void setUp() throws Exception
    {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        catch (InterruptedException exc) {}
    }

    @Test
    public void testNoNewsItems()
    {
        final PerformResult result = sendNewsJob.perform(null);
        Assert.assertEquals("Job did not perform correctly", CronJobResult.SUCCESS, result.getResult());
    }

    @Test
    public void testSendingNews() throws Exception
    {
        final NewsModel news1 = modelService.create(NewsModel.class);
        news1.setHeadline("test headline 1");
        news1.setContent("test content 1");
        news1.setDate(new Date());

        final NewsModel news2 = modelService.create(NewsModel.class);
        news2.setHeadline("test headline 2");
        news2.setContent("test content 2");
        news2.setDate(new Date());
        modelService.saveAll();

        final PerformResult result = sendNewsJob.perform(null);
        Assert.assertEquals("Job did not perform correctly", CronJobResult.SUCCESS, result.getResult());
    }

    @After
    public void tearDown() {

    }
}