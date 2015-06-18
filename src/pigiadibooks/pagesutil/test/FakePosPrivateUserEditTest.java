package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.pagesutil.FakePosPrivateUserEdit;

public class FakePosPrivateUserEditTest {

	@Test
	public void test() {
		String username="cesco";
		FakePosPrivateUserEdit edit=new FakePosPrivateUserEdit(username);
		try {
			edit.insertUser(
					new FakePosPrivateUserModel(username, "Francesco", "Giuliari", "pancettapepata"
							, "artix93@gmail.com", 13.7f, 73.1f,"user"));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		try {
			FakePosPrivateUserModel model= edit.getUserInfo();
			System.out.println(model);
			edit.updateInfo(model.getEmail(), model.getxPos(), 70.f);
			FakePosPrivateUserModel model2= edit.getUserInfo();
			System.out.println(model2);
			assertEquals(model.getEmail(), model2.getEmail());
			assertNotEquals(model.getyPos(), model2.getyPos());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		
	}

}
