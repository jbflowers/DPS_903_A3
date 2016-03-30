package bean.instructor;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class InstructorBean
 */
@Stateless
@LocalBean
public class InstructorBean extends InstructorBeanBase implements InstructorCommonBusiness {
	
	public InstructorBean() {
	
    }

}
