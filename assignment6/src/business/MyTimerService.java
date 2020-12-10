package business;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.Timeout;


//import org.jboss.logging.Logger;
import java.util.logging.Logger;


@Stateless
public class MyTimerService {

	@Resource
	TimerService timerService;
	
	
	private static final Logger logger = Logger.getLogger("business.MyTimerService"); 
    
	/**
     * Default constructor. 
     */
    public MyTimerService() {
        
    }
	
    //Creates a timer
    public void setTimer(long interval)
    {
    	timerService.createTimer(interval, "My Timer");
    }
    
    //Runs at the login page
    @Timeout
    public void programmaticTimeout(Timer timer)
    {
    	logger.info("@Timeout in programaticTimout()");
    }
    
   
    
    //Runs every 10 seconds after the user gets to the Data Table page
    //Wilfly11 doesn't like the 'info' when anything else has been changed, so I removed it.
  	//@Schedule(second="*/10", minute="*", hour="0-23", dayOfWeek="Mon-Fri",
  	//      dayOfMonth="*", month="*", year="*")
    //  private void scheduledTimeout(final Timer t) 
  	//{
    //      logger.info("@Schedule at scheduledTimeout!");     
    //}
  	


}