package lhj.js;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ModelReaderTest {

	@Test
	public void testReadModel() throws Exception {
		ModelReader reader = new ModelReader("src/main/resources/models.js");
		List<Model> models = reader.readModel();
		assertEquals(2, models.size());
		for (Model m : models) {
			System.out.println(m.getName());
		}
//		fail("Not yet implemented");
	}

}
